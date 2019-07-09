package com.purewhite.ywc.frame.ui.mvp;


import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public abstract class MvpActivity<DB extends ViewDataBinding,P extends PresenterImp>
        extends BaseMvpActivity<DB,P> {


    @Override
    protected void beforeView() {
        super.beforeView();
        initBar();
    }

    //设置状态栏
    protected void initBar()
    {
        BarUtils.obtianBarConfig().with(this)
                .setStatusBarHideFlag()
                .setStatusBarTextColorFlag(true)
                .build();
    }

    @Override
    protected void afterView() {
        super.afterView();
        BarUtils.obtianTitleConfig().setTitleBarPadding(onBarTitleView());
    }

    //延伸布局到状态栏里
    protected View onBarTitleView()
    {
        return null;
    }





}
