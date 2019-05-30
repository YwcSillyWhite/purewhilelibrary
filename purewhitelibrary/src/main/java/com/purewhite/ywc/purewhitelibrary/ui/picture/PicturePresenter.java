package com.purewhite.ywc.purewhitelibrary.ui.picture;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxSchedulers;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.picture.scanner.ImageScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author yuwenchao
 */
public class PicturePresenter extends PresenterImp<PictureContract.ViewUi>
        implements PictureContract.Presenter {

    @Override
    public void gainImg() {
        Observable.defer(new Callable<ObservableSource<List<ImageBean>>>() {
            @Override
            public ObservableSource<List<ImageBean>> call() throws Exception {
                List<ImageBean> imageBeanList=new ArrayList<>();
                if (mView!=null)
                {
                    ImageScanner imageScanner = new ImageScanner(mView.getContext());
                    imageBeanList.addAll(imageScanner.queryMedia());
                }
                return Observable.just(imageBeanList);
            }
        }).map(new Function<List<ImageBean>, List<Folder>>() {
            @Override
            public List<Folder> apply(List<ImageBean> imageBeans) throws Exception {
                List<Folder> folderList=new ArrayList<>();
                folderList.add(new Folder("全部图片",imageBeans));

                return folderList;
            }
        }).compose(RxSchedulers.<List<Folder>>ioToMain())
                .subscribe(new HttpObserver<List<Folder>>() {
                    @Override
                    protected void onSuccess(List<Folder> folders) {
                        mView.editImg(folders);
                    }
                });
    }


}
