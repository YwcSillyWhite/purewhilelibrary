package com.purewhite.ywc.purewhitelibrary.ui.picture.presenter;

import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxSchedulers;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.picture.contract.PictureSelectContract;
import com.purewhite.ywc.purewhitelibrary.ui.picture.scanner.ImageScanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class PictureSelectPresenter extends BasePresenter<PictureSelectContract.UiView>
        implements PictureSelectContract.Presenter {


    @Override
    public void requestList() {

        Observable.create(new ObservableOnSubscribe<List<ImageBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ImageBean>> emitter) throws Exception {
                emitter.onNext(new ImageScanner().queryMedia());
            }
        }).map(new Function<List<ImageBean>, List<Folder>>() {
            @Override
            public List<Folder> apply(List<ImageBean> imageBeans) throws Exception {
                List<Folder> folderList=new ArrayList<>();
                if (imageBeans!=null&&imageBeans.size()>0)
                {
                    folderList.add(new Folder("全部",imageBeans));
                    for (int i = 0; i < imageBeans.size(); i++) {
                        obtianListFolder(folderList,imageBeans.get(i),false);
                    }
                }

                return folderList;
            }

        }).compose(RxSchedulers.ioToMain()).subscribe(new HttpObserver<List<Folder>>(mView.getContext()) {
            @Override
            protected void onSuccess(List<Folder> folders) {
                mView.responList(folders);
            }
        });
    }



    public void obtianListFolder(List<Folder> list,@NonNull ImageBean imageBean,boolean insert)
    {
        if (insert)
        {
            Folder folder = obtianFolder("全部", list);
            folder.getImageBeanList().add(0,imageBean);

        }
        String floderName = obtianFolderName(imageBean);
        if (!TextUtils.isEmpty(floderName))
        {
            Folder folder = obtianFolder(floderName, list);
            if (insert)
            {
                folder.getImageBeanList().add(0,imageBean);
            }
            else
            {
                folder.getImageBeanList().add(imageBean);
            }
        }
    }


    /**
     * 获取集体folder
     * @param folderName
     * @param list
     * @return
     */
    private Folder obtianFolder(String folderName,List<Folder> list)
    {
        //查看文件list存在这个文件名不
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(folderName))
            {

                return list.get(i);
            }
        }
        Folder folder = new Folder(folderName, new ArrayList<ImageBean>());
        list.add(folder);
        return folder;
    }


    /**
     * 获取文件夹名字
     * @param imageBean
     * @return
     */
    private String obtianFolderName(ImageBean imageBean)
    {
        String path = imageBean.getPath();
        if (!TextUtils.isEmpty(path))
        {
            String[] fileName = path.split(File.separator);
            if (fileName.length > 1) {
                return fileName[fileName.length - 2];
            }
        }
        return "";
    }

}
