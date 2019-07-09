package com.purewhite.ywc.purewhitelibrary.adapter.swipe;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


/**
 *
 * @author yuwenchao
 */
public abstract class BaseSwipeRefreshLayout extends SwipeRefreshLayout {

    public BaseSwipeRefreshLayout(@NonNull Context context) {
        this(context,null);
    }

    public BaseSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //下拉刷新的圆圈是否显示
        setRefreshing(true);
        //设置下拉时圆圈的背景颜色（这里设置成透明）
        setProgressBackgroundColorSchemeResource(android.R.color.white);
        //设置load加载速度
        setSize(SwipeRefreshLayout.DEFAULT);
        //设置开始不能加载
        setEnabled(false);
    }


    public void setOnLoadLinstener(SwipLoadListener swipLoadListener)
    {
        setOnRefreshListener(swipLoadListener);
    }


    public void refreshComplete(int page)
    {
        refreshComplete(page==1);
    }

    public void refreshComplete(boolean flush)
    {
        if (flush)
        {
            if (!isEnabled())
            {
                setEnabled(true);
            }
            setRefreshing(false);
        }
    }
}
