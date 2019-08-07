package com.purewhite.ywc.purewhitelibrary.mvp.activity;


import android.view.View;

import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.config.event.BaseEvent;
import com.purewhite.ywc.purewhitelibrary.config.event.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 *
 * 状态栏和导航拦设置相关activity
 */
public abstract class BaseBarEventbusActivity extends BaseActivity{


    @Override
    protected void beforeView() {
        super.beforeView();
        if (isBar())
        {
            initBar();
        }
        if (isEventBus())
        {
            EventBusUtils.register(this);
        }
    }

    //默认是处理bar的
    protected boolean isBar()
    {
        return true;
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


    protected int getTitleBarId()
    {
        return 0;
    }





    //默认没有开启eventbus
    protected boolean isEventBus()
    {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    private void receiveEvent(BaseEvent baseEvent)
    {
        if (baseEvent!=null)
        {
            receiveEventBus(baseEvent);
        }
    }

    protected void receiveEventBus(BaseEvent baseEvent)
    {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isEventBus())
        {
            EventBusUtils.unregister(this);
        }
    }
}
