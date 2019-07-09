package com.purewhite.ywc.purewhitelibrary.adapter.swipe;


import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author yuwenchao
 */
public abstract class SwipLoadListener implements SwipeRefreshLayout.OnRefreshListener {

    //判断能否下啦刷新
    public boolean judgeLoad()
    {
        return true;
    }

    //下啦刷新
    public abstract void pullDown();

    @Override
    public void onRefresh() {
        if (judgeLoad())
        {
            pullDown();
        }
    }


}
