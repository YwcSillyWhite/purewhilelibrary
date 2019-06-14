package com.purewhite.ywc.frame.ui.fragment.home.child;

import com.purewhite.ywc.frame.ui.adapter.HomeChildAdapter;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;

public class HomeChildContract {

    interface UiView extends BaseUiView
    {
        HomeChildAdapter getAdapter();

        void respon(boolean flush);
    }

    interface Presenter extends BasePresenter<UiView>
    {
        void obtianShopList(int position,boolean flush);
    }
}
