package com.purewhite.ywc.purewhitelibrary.adapter.ptr.head;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.purewhite.ywc.purewhitelibrary.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * @author yuwenchao
 */
public class PtrFrameHead extends FrameLayout implements PtrUIHandler {

    private AnimationDrawable refreshAnim;

    public PtrFrameHead(@NonNull Context context) {
        super(context);
        init();
    }

    public PtrFrameHead(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PtrFrameHead(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化
    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ptr_head, this, true);
        ImageView ivRefreshView = view.findViewById(R.id.ptr_Img);
        refreshAnim = (AnimationDrawable) ivRefreshView.getBackground();

    }

    //初始化
    @Override
    public void onUIReset(PtrFrameLayout frame) {
        stopAnim();
    }

    //准备
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    //开始
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        if(refreshAnim!=null && !refreshAnim.isRunning()){
            refreshAnim.start();
        }
    }

    //结束
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        stopAnim();
    }

    //变化
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }

    private void stopAnim()
    {
        if(refreshAnim!=null && refreshAnim.isRunning()){
            refreshAnim.stop();
        }
    }
}
