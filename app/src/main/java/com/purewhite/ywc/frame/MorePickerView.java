package com.purewhite.ywc.frame;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;

import java.util.ArrayList;
import java.util.List;

public class MorePickerView extends LinearLayout implements OnItemSelectedListener{


    private List<WheelView> wheelViewList=new ArrayList<>();

    private OnItemSelectedListener onItemSelectedListener1=new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int index) {

        }
    };


    private OnItemSelectedListener onItemSelectedListener2=new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int index) {

        }
    };


    private OnItemSelectedListener onItemSelectedListener3=new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int index) {

        }
    };


    private OnItemSelectedListener onItemSelectedListener4=new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int index) {

        }
    };


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
        initWheelNum();
    }

    private void initWheelNum() {
        setWeightSum(4);
        for (int i = 0; i < 4; i++) {
            WheelView wheelView = new WheelView(getContext());
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight=1;
            addView(wheelView,layoutParams);
            switch (i)
            {
                case 0:
                    wheelView.setOnItemSelectedListener(onItemSelectedListener1);
                    break;
                case 1:
                    wheelView.setOnItemSelectedListener(onItemSelectedListener2);
                    break;
                case 2:
                    wheelView.setOnItemSelectedListener(onItemSelectedListener3);
                    break;
                case 3:
                    wheelView.setOnItemSelectedListener(onItemSelectedListener4);
                    break;
            }
        }
    }

    @Override
    public void onItemSelected(int index) {

    }
}
