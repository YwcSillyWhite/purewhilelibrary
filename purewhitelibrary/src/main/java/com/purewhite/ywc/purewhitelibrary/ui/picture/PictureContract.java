package com.purewhite.ywc.purewhitelibrary.ui.picture;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;

/**
 * @author yuwenchao
 */
public class PictureContract {

    interface ViewUi extends BaseUiView
    {
        PictureAdapter getAdapter();
    }

    interface Presenter extends BasePresenter<ViewUi>
    {
        void gainImg();
    }
}
