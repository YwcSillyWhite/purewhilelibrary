package com.purewhite.ywc.purewhitelibrary.mvp.activity;


import android.view.View;

import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;

/**
 *
 * 状态栏和导航拦设置相关activity
 */
public abstract class BaseBarActivity extends BaseActivity{


    @Override
    protected void beforeView() {
        super.beforeView();
        initBar();
    }

    //沉淀状态栏
    protected void initBar()
    {
        BarUtils.obtianBarConfig().with(this)
                .setStatusBar(1)
                .setStatusBarTextColorFlag(true)
                .build();
    }


    @Override
    protected void afterView() {
        super.afterView();
        initTitleBar();
    }

    //延伸布局高度适应沉淀状态栏
    private void initTitleBar() {
        BarUtils.obtianTitleConfig().setTitleBarPadding(getTitleBarView());
    }

    protected View getTitleBarView()
    {
        return findViewById(getTitleBarId());
    }

    protected abstract int getTitleBarId();

}
