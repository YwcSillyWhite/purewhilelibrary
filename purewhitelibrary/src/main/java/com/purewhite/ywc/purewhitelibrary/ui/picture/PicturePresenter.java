package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxSchedulers;

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
                List<ImageBean> imageBeanList = new ArrayList<>();
                if (mView!=null)
                {
                    Context context = mView.getContext();
                    Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    ContentResolver mContentResolver = context.getContentResolver();
                    Cursor mCursor = mContentResolver.query(mImageUri, new String[]{
                                    MediaStore.Images.Media.DATA,
                                    MediaStore.Images.Media.DISPLAY_NAME,
                                    MediaStore.Images.Media.DATE_ADDED,
                                    MediaStore.Images.Media._ID},
                            null,
                            null,
                            MediaStore.Images.Media.DATE_ADDED);
                    if (mCursor!=null&&mCursor.getCount()>0)
                    {
                        //读取扫描到的图片
                        while (mCursor.moveToNext()) {
                            // 获取图片的路径
                            String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            //获取图片名称
                            String name = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                            //获取图片时间
                            long time = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
                            imageBeanList.add(new ImageBean(path,name,time));
                        }
                    }
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
                       mView.getAdapter().flush(folders.get(0).getImageBeanList());
                    }
                });
    }


}
