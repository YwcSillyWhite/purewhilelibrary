package com.purewhite.ywc.purewhitelibrary.ui.picture.contract;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.IBasePurePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.IBasePureView;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;

import java.util.List;

public class PictureSelectContract {

    public interface UiView extends IBasePureView
    {
        void responList(List<Folder> folderList);
    }


    public interface Presenter extends IBasePurePresenter<UiView>
    {
        void requestList();
    }
}
