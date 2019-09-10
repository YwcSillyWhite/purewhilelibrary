package com.purewhite.ywc.frame.ui.fragment.home.child;

import com.purewhite.ywc.frame.ui.adapter.HomeChildAdapter;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.IBasePurePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.IBasePureView;

public class HomeChildContract {

    interface UiView extends IBasePureView
    {
        HomeChildAdapter getAdapter();

        void respon(boolean flush);
    }

    interface Presenter extends IBasePurePresenter<UiView>
    {
        void obtianShopList(int position,boolean flush);
    }
}
