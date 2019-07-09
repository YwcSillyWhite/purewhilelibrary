package com.purewhite.ywc.purewhitelibrary.view.imageview;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.purewhite.ywc.purewhitelibrary.R;



/**
 * 等比例图片
 * @author yuwenchao
 */
public class RatioImageView extends AppCompatImageView {
    private int high=1,wide=1;
    public RatioImageView(Context context) {
        this(context,null);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RatioImageView);
            high=typedArray.getInt(R.styleable.RatioImageView_high,1);
            wide=typedArray.getInt(R.styleable.RatioImageView_wide,1);
            typedArray.recycle();
        }
    }


    /**
     * UNSPECIFIED：不对View大小做限制，如：ListView，ScrollView
     * EXACTLY：确切的大小，如：100dp或者march_parent
     * AT_MOST：大小不可超过某数值，如：wrap_content
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // 如果子类设置了精确的宽高
        if (widthMode==MeasureSpec.EXACTLY)
        {
            heightSize= ((int) (widthSize * 1.0f * wide / high));
            setMeasuredDimension(widthSize, heightSize);
            return;
        }
        else if (heightMode==MeasureSpec.EXACTLY)
        {
            widthSize= ((int) (heightSize * 1.0f * high / wide));
            setMeasuredDimension(widthSize,heightSize);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
