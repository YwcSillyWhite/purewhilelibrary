package com.purewhite.ywc.purewhitelibrary.view.viewpager;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.purewhite.ywc.purewhitelibrary.R;

/**
 * viewpager 禁止滑动
 * @author yuwenchao
 */
public class NoSlideViewPager extends ViewPager {

    private boolean slide;

    public void setSlide(boolean slide) {
        this.slide = slide;
    }

    public NoSlideViewPager(@NonNull Context context) {
        super(context);
    }

    public NoSlideViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NoSlideViewPager);
            slide=typedArray.getBoolean(R.styleable.NoSlideViewPager_slide,false);
            typedArray.recycle();
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return slide?super.onInterceptTouchEvent(ev):slide;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return slide?super.onTouchEvent(ev):slide;
    }
}
