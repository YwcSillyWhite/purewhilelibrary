package com.purewhite.ywc.frame.wheel.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.wheel.bean.WheelBean;
import com.purewhite.ywc.frame.wheel.callback.WheelCallBack;

import java.util.ArrayList;
import java.util.List;

public class MorePickerView extends LinearLayout{


    private List<WheelView> wheelViewList=new ArrayList<>();
    private WheelCallBack wheelCallBack;

    public void setWheelCallBack(WheelCallBack wheelCallBack) {
        this.wheelCallBack = wheelCallBack;
    }

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
            int wheelNum = typedArray.getInt(R.styleable.MorePickerView_wheelNum, 0);
            initWheelNum(wheelNum);
        }

    }

    private void initWheelNum(int wheelNum) {
        setWeightSum(wheelNum);
        for (int i = 0; i < wheelNum; i++) {
            WheelView wheelView = new WheelView(getContext());
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight=1;
            addView(wheelView,layoutParams);
            wheelViewList.add(wheelView);
        }
    }


    public void setWheelAdapterPosition(int position,WheelAdapter wheelAdapter)
    {
        if (position<wheelViewList.size())
        {
            WheelView wheelView = wheelViewList.get(position);
            if (wheelAdapter!=null)
            {
                wheelView.setAdapter(wheelAdapter);
                wheelView.setVisibility(VISIBLE);
            }
            else
            {
                wheelView.setVisibility(INVISIBLE);
            }

        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener ...onItemSelectedListeners)
    {
        if (wheelViewList.size()==onItemSelectedListeners.length)
        {
            for (int i = 0; i < onItemSelectedListeners.length; i++) {
                wheelViewList.get(i).setOnItemSelectedListener(onItemSelectedListeners[i]);
            }
        }
    }


    /**
     *
     */
    public void obtianData()
    {
        if (wheelCallBack!=null)
        {
            List<Integer> list=new ArrayList<>();
            for (int i = 0; i < wheelViewList.size(); i++) {
                WheelView wheelView = wheelViewList.get(i);
                if (wheelView.getVisibility()==VISIBLE)
                {
                    list.add(i);
                }
                else
                {
                    break;
                }
            }
            wheelCallBack.callBack(list);
        }

    }


}
