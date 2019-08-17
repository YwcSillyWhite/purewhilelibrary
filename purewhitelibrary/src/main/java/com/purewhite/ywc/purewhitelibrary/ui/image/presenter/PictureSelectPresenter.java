package com.purewhite.ywc.purewhitelibrary.ui.image.presenter;

import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxSchedulers;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.image.contract.PictureSelectContract;
import com.purewhite.ywc.purewhitelibrary.ui.image.scanner.ImageScanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

public class PictureSelectPresenter extends PresenterImp<PictureSelectContract.UiView>
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
                        ImageBean imageBean = imageBeans.get(i);
                        String folderName = getFolderName(imageBean.getPath());
                        if (!TextUtils.isEmpty(folderName))
                        {
                            Folder folder = getFolder(folderName, folderList);
                            folder.getImageBeanList().add(imageBean);
                        }
                    }
                }

                return folderList;
            }

            /**
             * 获取文件夹名字
             * @param path
             * @return
             */
            private String getFolderName(String path) {
                if (!TextUtils.isEmpty(path)) {
                    String[] strings = path.split(File.separator);
                    if (strings.length > 1) {
                        return strings[strings.length - 2];
                    }
                }
                return "";
            }

            /**
             * 获取folder
             * @param folderName
             * @param list
             * @return
             */
            private Folder getFolder(String folderName,List<Folder> list)
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

        }).compose(RxSchedulers.ioToMain()).subscribe(new HttpObserver<List<Folder>>(mView.getContext()) {
            @Override
            protected void onSuccess(List<Folder> folders) {
                mView.responList(folders);
            }
        });
    }



}
