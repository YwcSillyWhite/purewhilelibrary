package com.purewhite.ywc.purewhitelibrary.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Created by HuiRan on 2018/6/1 12:20
 * E-Mail: 15260828327@163.com
 * description:
 */
public class PointLoadingView extends View {

    private int mPointDiameter = SizeUtils.dip2px(9);
    private int mSpace = SizeUtils.dip2px(3);
    private int count = 3;
    private List<Point> mPointList;
    private List<Paint> mPaintList;
    private int mAreWidth;
    private int mAreHeight;
    private Interpolator mInterpolator = new LinearInterpolator();
    private float mProgress;
    private ValueAnimator mAnimator;

    public PointLoadingView(Context context) {
        this(context, null);
    }

    public PointLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaintList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            /**
             * Paint.ANTI_ALIAS_FLAG ：抗锯齿标志
             * Paint.FILTER_BITMAP_FLAG : 使位图过滤的位掩码标志
             * Paint.DITHER_FLAG : 使位图进行有利的抖动的位掩码标志
             * Paint.UNDERLINE_TEXT_FLAG : 下划线
             * Paint.STRIKE_THRU_TEXT_FLAG : 中划线
             * Paint.FAKE_BOLD_TEXT_FLAG : 加粗
             * Paint.LINEAR_TEXT_FLAG : 使文本平滑线性扩展的油漆标志
             * Paint.SUBPIXEL_TEXT_FLAG : 使文本的亚像素定位的绘图标志
             * Paint.EMBEDDED_BITMAP_TEXT_FLAG : 绘制文本时允许使用位图字体的绘图标志
             * ---------------------
             */
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setDither(true);
            /**
             * Paint.Style.STROKE 只绘制图形轮廓（描边）
             * Paint.Style.FILL 只绘制图形内容
             * Paint.Style.FILL_AND_STROKE 既绘制轮廓也绘制内容
             */
            paint.setStyle(Paint.Style.FILL);
            if (i == 0) {
                paint.setColor(Color.parseColor("#FFFF664A"));
            } else if (i == 1) {
                paint.setColor(Color.parseColor("#FFFFD100"));
            } else {
                paint.setColor(Color.parseColor("#FF5E8EF4"));
            }
            mPaintList.add(paint);
        }
        mAreWidth = mPointDiameter * count + mSpace * (count - 1);
        mAreHeight = mPointDiameter;
    }


    /**
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * 1.精确模式（MeasureSpec.EXACTLY）
     * 在这种模式下，尺寸的值是多少，那么这个组件的长或宽就是多少。
     *
     * 2.最大模式（MeasureSpec.AT_MOST）
     * 这个也就是父组件，能够给出的最大的空间，当前组件的长或宽最大只能为这么大，当然也可以比这个小。
     *
     * 3.未指定模式（MeasureSpec.UNSPECIFIED）
     * 这个就是说，当前组件，可以随便用空间，不受限制
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;
        width = widthMode == MeasureSpec.EXACTLY ? widthSize : mAreWidth;
        height = heightMode == MeasureSpec.EXACTLY ? heightSize : mAreHeight;
        setMeasuredDimension(width, height);
    }


    /**
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     * onSizeChanged() 在控件大小发生改变时调用。所以这里初始化会被调用一次
     * 作用：获取控件的宽和高度
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int paddingHorizontal = (getWidth() - mAreWidth) / 2;
        int paddingVertical = (getHeight() - mAreHeight) / 2;
        Rect rect = new Rect(getPaddingLeft() + paddingHorizontal,
                getPaddingTop() + paddingVertical,
                getWidth() - getPaddingRight() - paddingHorizontal,
                getHeight() - getPaddingBottom() - paddingVertical);
        mPointList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Point point = new Point(rect.left + mPointDiameter / 2 * (i + 1) +
                    i * mSpace + i * mPointDiameter / 2,
                    rect.centerY());
            mPointList.add(point);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float maxRadius = mPointDiameter / 2.0f;
        float minRadius = maxRadius / count;
        for (int i = 0; i < count; i++) {
            Point point = mPointList.get(i);
            float currentRadius = maxRadius * (i + 1) / count;
            float radius;
            float fraction;
            float percent = 1.0f - (i * 1.0f / (count - 1));
            if (mProgress < percent) {//放大
                fraction = (mProgress - 0.0f) / (percent - 0.0f);
                radius = currentRadius * (1.0f - fraction) + fraction * maxRadius;
            } else {//缩小
                fraction = (mProgress - percent) / (1.0f - percent);
                radius = maxRadius * (1.0f - fraction) + fraction * minRadius;
            }
            canvas.drawCircle(point.x, point.y, radius, mPaintList.get(i));
        }
    }

    public void startLoading() {
        if (mAnimator==null)
        {
            mAnimator = ValueAnimator.ofFloat(0f, 1.0f).setDuration(1000);
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mProgress = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mAnimator.setInterpolator(mInterpolator);
            mAnimator.setRepeatMode(ValueAnimator.REVERSE);
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        }
        mAnimator.start();
    }


    public void stopLoading() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLoading();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLoading();
    }




}
