package com.purewhite.ywc.purewhitelibrary.ui.image.contract;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.Folder;

import java.util.List;

public class PictureSelectContract {

    public interface UiView extends BaseUiView
    {
        void responList(List<Folder> folderList);
    }


    public interface Presenter extends BasePresenter<UiView>
    {
        void requestList();
    }
}
