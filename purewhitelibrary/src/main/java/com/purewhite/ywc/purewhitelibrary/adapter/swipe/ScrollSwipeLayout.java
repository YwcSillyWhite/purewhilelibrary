package com.purewhite.ywc.purewhitelibrary.adapter.swipe;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.purewhite.ywc.purewhitelibrary.R;


/**
 * @author yuwenchao
 */
public class ScrollSwipeLayout extends BaseSwipeRefreshLayout{

    //是否解决滑动冲突
    private boolean scroll=true;
    //滑动的时候上次xy位置
    private float lastX,lastY;

    public ScrollSwipeLayout(@NonNull Context context) {
        this(context,null);
    }

    public ScrollSwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ScrollSwipeLayout);
            scroll = typedArray.getBoolean(R.styleable.ScrollSwipeLayout_scroll_swipe, true);
            typedArray.recycle();
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (scroll)
        {
            switch (ev.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    lastX=ev.getX();
                    lastY=ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    final float afterX = ev.getX();
                    final float afterY = ev.getY();
                    float moveX = Math.abs(afterX - lastX);
                    float moveY = Math.abs(afterY - lastY);
                    //如果x移动大于y移动就拦截
                    if (moveX>moveY)
                    {
                        return false;
                    }
                    break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }



}
