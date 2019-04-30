package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.scroll;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;


/**
 * 滑动不加载图片
 * @author yuwenchao
 *
 */
public class OnScrollLoadListener extends RecyclerView.OnScrollListener {
    //滑动加载
    private boolean slideLoad;
    public void setSlideLoad(boolean slideLoad) {
        this.slideLoad = slideLoad;
    }
    /**
     *
     * @param recyclerView
     * @param newState  滚动状态
     *  SCROLL_STATE_IDLE=0  （静止没有滚动）
     *  SCROLL_STATE_DRAGGING=1    （正在被外部拖拽,一般为用户正在用手指滚动）
     *  SCROLL_STATE_SETTLING=2      （自动滚动）
     */
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (!slideLoad)
        {
            if (newState==RecyclerView.SCROLL_STATE_IDLE)
            {
                ImageLoader.newInstance().start(recyclerView.getContext());
            }
            else
            {
                ImageLoader.newInstance().stop(recyclerView.getContext());
            }
        }

    }
}
