package com.purewhite.ywc.purewhitelibrary.ui.picture;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxSchedulers;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.image.scanner.ImageScanner;
import com.purewhite.ywc.purewhitelibrary.ui.picture.config.PictureUtils;

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
                    ImageScanner imageScanner = new ImageScanner();
                    imageBeanList.addAll(imageScanner.queryMedia());
                }
                return Observable.just(imageBeanList);
            }
        }).map(new Function<List<ImageBean>, List<Folder>>() {
            @Override
            public List<Folder> apply(List<ImageBean> imageBeans) throws Exception {
                return PictureUtils.getFolderList(imageBeans);
            }
        }).compose(RxSchedulers.<List<Folder>>ioToMain())
                .subscribe(new HttpObserver<List<Folder>>(mView.getContext()) {
                    @Override
                    protected void onSuccess(List<Folder> folders) {
                        mView.obtianListFolder(folders);
                    }
                });
    }


}
