package com.purewhite.ywc.purewhitelibrary.adapter.ptr.callback;

import android.view.View;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @author yuwenchao
 */
public abstract class PtrLoadListener extends PtrDefaultHandler {
    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        pullDown();
    }

    //下啦刷新
    public abstract void pullDown();


    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return judge()&&super.checkCanDoRefresh(frame, content, header);
    }

    //判断能否下啦刷新
    public boolean judge()
    {
        return true;
    }
}
