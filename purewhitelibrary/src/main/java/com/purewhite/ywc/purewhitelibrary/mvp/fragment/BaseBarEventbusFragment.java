package com.purewhite.ywc.purewhitelibrary.mvp.fragment;

import android.view.View;

import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.config.event.BaseEvent;
import com.purewhite.ywc.purewhitelibrary.config.event.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * 状态栏和导航拦设置相关activity
 */
public abstract class BaseBarEventbusFragment extends BaseFragment{

    @Override
    protected void beforView() {
        super.beforView();
        if (isEventBus())
        {
            EventBusUtils.register(this);
        }
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
        return view.findViewById(getTitleBarId());
    }

    protected  int getTitleBarId()
    {
        return 0;
    }



    //默认没有开启eventbus
    protected boolean isEventBus()
    {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void receiveEvent(BaseEvent baseEvent)
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
    public void onDestroy() {
        super.onDestroy();
        if (isEventBus())
        {
            EventBusUtils.unregister(this);
        }

    }

}
