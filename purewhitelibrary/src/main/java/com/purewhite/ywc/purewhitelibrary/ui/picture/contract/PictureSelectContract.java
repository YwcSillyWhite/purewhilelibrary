package com.purewhite.ywc.purewhitelibrary.ui.picture.contract;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.IBasePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.IBaseView;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;

import java.util.List;

public class PictureSelectContract {

    public interface UiView extends IBaseView
    {
        void responList(List<Folder> folderList);
    }


    public interface Presenter extends IBasePresenter<UiView>
    {
        void requestList();
    }
}
