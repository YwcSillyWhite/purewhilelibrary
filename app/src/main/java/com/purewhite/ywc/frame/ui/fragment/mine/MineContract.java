package com.purewhite.ywc.frame.ui.fragment.mine;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;

public class MineContract {

    interface UiView extends BaseUiView
    {

    }

    interface Presenter extends BasePresenter<UiView>
    {
        void obtianShop();
    }
}
