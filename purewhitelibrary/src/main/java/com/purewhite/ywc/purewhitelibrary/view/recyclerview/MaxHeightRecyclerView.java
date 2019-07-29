package com.purewhite.ywc.purewhitelibrary.view.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.purewhitelibrary.R;

public class MaxHeightRecyclerView extends RecyclerView {

    private int maxHeight;

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }
    public MaxHeightRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public MaxHeightRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MaxHeightRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MaxHeightRecyclerView);
            maxHeight = typedArray.getLayoutDimension(R.styleable.MaxHeightRecyclerView_maxHeight, 0);
            typedArray.recycle();
        }

    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        if (maxHeight>0)
        {
            heightSpec=MeasureSpec.makeMeasureSpec(maxHeight,MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthSpec, heightSpec);
    }



}
