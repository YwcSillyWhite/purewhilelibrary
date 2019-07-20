package com.purewhite.ywc.frame;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;

import java.util.List;

public class MorePickerView extends LinearLayout implements OnItemSelectedListener{

    private int wheelNum;

    public MorePickerView(Context context) {
        this(context,null);
    }

    public MorePickerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MorePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MorePickerView);
            wheelNum = typedArray.getInt(R.styleable.MorePickerView_wheelNum, 0);
            typedArray.recycle();
        }

        initWheelNum(wheelNum);
    }

    private void initWheelNum(int wheelNum) {
        if (wheelNum>0)
        {
            setWeightSum(wheelNum);
            for (int i = 0; i < wheelNum; i++) {
                WheelView wheelView = new WheelView(getContext());
                LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.weight=1;
                addView(wheelView,layoutParams);
                wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int index) {

                    }
                });
//                wheelView.setAdapter();

            }
        }
    }

    @Override
    public void onItemSelected(int index) {

    }
}
