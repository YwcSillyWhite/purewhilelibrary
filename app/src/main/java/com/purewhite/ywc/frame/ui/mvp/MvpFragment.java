package com.purewhite.ywc.frame.ui.mvp;


import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.fragment.BaseMvpFragment;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public abstract class MvpFragment<DB extends ViewDataBinding,P extends PresenterImp>
        extends BaseMvpFragment<DB,P> {


    protected View onBarTitleView()
    {
        return null;
    }


    @Override
    protected void afterView() {
        super.afterView();
        BarUtils.obtianTitleConfig().setTitleBarPadding(onBarTitleView());

    }

}
