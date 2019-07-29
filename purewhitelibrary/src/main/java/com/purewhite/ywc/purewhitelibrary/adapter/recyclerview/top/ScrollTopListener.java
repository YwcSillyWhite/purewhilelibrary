package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.top;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.scroll.OnScrollLoadListener;
import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;


/**
 * 置顶滑动监听
 * @author yuwenchao
 */
public class ScrollTopListener extends OnScrollLoadListener {

    private ScrollTopHelp scrollTopHelp;
    //这个position是代表几个手机高度
    private int scrollY=0;
    private int maxHeight;
    private RecyclerView recyclerView;

    public ScrollTopListener(View imageView)
    {
        this(imageView,2);
    }

    public ScrollTopListener(View imageView,int position)
    {
        if (imageView==null)
        {
            throw new UnsupportedOperationException("view is null");
        }
        this.scrollTopHelp=new ScrollTopHelp(imageView);
        position=position>0?position:2;
        maxHeight = SizeUtils.getScreenHeight()*position;
        imageView.setOnClickListener(onClickListener);
    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (this.recyclerView==null)
        {
            this.recyclerView=recyclerView;
        }
        scrollY+=dy;
        if (scrollY>=maxHeight)
        {
            scrollTopHelp.moveIn();
        }
        else
        {
            scrollTopHelp.moveOut();
        }
    }

    public void release() {
        scrollTopHelp.release();
    }

    private View.OnClickListener onClickListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            if (recyclerView != null) {
                scrollY=0;
                recyclerView.scrollToPosition(0);
            }
        }
    };
}
