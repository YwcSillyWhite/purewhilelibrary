package com.purewhite.ywc.frame1.ui.fragment.home;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;

public class HomeContract {

    interface UiView extends BaseUiView
    {

    }

    interface Presenter extends BasePresenter<UiView>
    {

    }
}
