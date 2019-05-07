package com.purewhite.ywc.purewhitelibrary.adapter.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * //解决与viewpager使用的时候滑动冲突
 * @author yuwenchao
 */
public class ScrollPtrLayout extends PtrLayout{

    //上一次x,y的位置；
    private float lastX,lastY;
    private float moveX,moveY;

    public ScrollPtrLayout(Context context) {
        super(context);
    }

    public ScrollPtrLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollPtrLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastX=ev.getX();
                lastY=ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float afterX = ev.getX();
                final float afterY = ev.getY();
                moveX+=Math.abs(afterX-lastX);
                moveY+=Math.abs(afterY-lastY);
                lastX=afterX;
                lastY=afterY;
                //如果x移动大于y移动就拦截
                if (moveX>moveY)
                {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
