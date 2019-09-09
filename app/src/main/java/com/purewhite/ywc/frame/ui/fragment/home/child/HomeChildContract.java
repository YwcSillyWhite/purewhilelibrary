package com.purewhite.ywc.frame.ui.fragment.home.child;

import com.purewhite.ywc.frame.ui.adapter.HomeChildAdapter;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.IBasePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.IBaseUiView;

public class HomeChildContract {

    interface UiView extends IBaseUiView
    {
        HomeChildAdapter getAdapter();

        void respon(boolean flush);
    }

    interface Presenter extends IBasePresenter<UiView>
    {
        void obtianShopList(int position,boolean flush);
    }
}
