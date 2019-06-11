package com.purewhite.ywc.purewhitelibrary.view.scrollview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * @author yuwenchao
 */
public class PureScrollView extends ClashScrollView {

    private View childView;
    private boolean isAnim;
    //用来纪录位置
    private Rect rect=new Rect();
    public PureScrollView(Context context) {
        super(context);
    }

    public PureScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PureScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount()>0)
        {
            //子类
            childView=getChildAt(0);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try
        {
            return touchEvent(ev);
        }
        catch (Exception e)
        {

        }
        return false;
    }



    private boolean touchEvent(MotionEvent ev) throws Exception
    {
        if (childView!=null&&!isAnim)
        {
            int newY = (int) ev.getY();
            if (ev.getAction()==MotionEvent.ACTION_MOVE)
            {
                int moveY=newY-lastY;
                if (isMove())
                {

                    //rect没有纪录值
                    if (rect.isEmpty())
                    {
                        //纪录当前位置
                        rect.set(childView.getLeft(),childView.getTop(),childView.getRight(),childView.getBottom());
                    }
                    childView.layout(childView.getLeft(),childView.getTop()+moveY/2,childView.getRight(),childView.getBottom()+moveY/2);
                }
            }
            else if (ev.getAction()==MotionEvent.ACTION_UP)
            {
                if (!rect.isEmpty())
                {
                    anim();
                }
            }
            lastY=newY;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 是否可以移动
     * @return
     */
    private boolean isMove()
    {
        int child_MeasureHeight = childView.getMeasuredHeight();
        int measuredHeight = getMeasuredHeight();
        int move=child_MeasureHeight-measuredHeight;
        float scaleY = getScaleY();
        if (scaleY<=0||scaleY>=move)
        {
            return true;
        }
        return false;
    }


    /**
     * 结束返回动画
     */
    private void anim()
    {
        int translateY = rect.bottom - childView.getBottom();
        TranslateAnimation animation=new TranslateAnimation(0,0,0,translateY);
        animation.setDuration(200);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnim=true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnim=false;
                childView.clearAnimation();
                childView.layout(rect.left,rect.top,rect.right,rect.bottom);
                rect.setEmpty();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        childView.startAnimation(animation);
    }
}
