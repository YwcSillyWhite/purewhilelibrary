package com.purewhite.ywc.purewhitelibrary.mvp.fragment;

import android.view.View;

import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;


/**
 * 状态栏和导航拦设置相关activity
 */
public abstract class BaseBarFragment extends BaseFragment{

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
        return view.findViewById(getTitleBarId());
    }

    protected abstract int getTitleBarId();

}
