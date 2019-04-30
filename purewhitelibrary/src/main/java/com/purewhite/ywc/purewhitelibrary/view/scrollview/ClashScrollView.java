package com.purewhite.ywc.purewhitelibrary.view.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 解决滑动冲突
 * @author yuwenchao
 */
public class ClashScrollView extends ScrollView {

    private int lastX,lastY;

    public ClashScrollView(Context context) {
        super(context);
    }

    public ClashScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClashScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept=false;
        int newX = (int) ev.getX();
        int newY = (int) ev.getY();
        if (ev.getAction()==MotionEvent.ACTION_MOVE)
        {
            int moveX = Math.abs(newX-lastX);
            int moveY = Math.abs(newY-lastY);
            if (moveY>moveX)
                intercept=true;
        }
        lastX=newX;
        lastY=newY;
        return intercept;
    }
}
