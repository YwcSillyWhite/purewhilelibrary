package com.purewhite.ywc.frame.wheel.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.wheel.adapter.BaseWheelAdapter;
import com.purewhite.ywc.frame.wheel.adapter.BaseWheelAdapterImp;
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
            boolean cyc_scroll = typedArray.getBoolean(R.styleable.MorePickerView_cyc_scroll, false);
            int textSize = typedArray.getInt(R.styleable.MorePickerView_wheel_textsize, 0);
            typedArray.recycle();
            initWheelNum(wheelNum,cyc_scroll,textSize);

        }

    }

    public void setWheelColor(int seletorColor,int noSeletorColore)
    {
        for (int i = 0; i < wheelViewList.size(); i++) {
            WheelView wheelView = wheelViewList.get(i);
            wheelView.setTextColorCenter(seletorColor);
            wheelView.setTextColorOut(noSeletorColore);
        }
    }

    public void setDivider(int color, WheelView.DividerType dividerType)
    {
        for (int i = 0; i < wheelViewList.size(); i++) {
            WheelView wheelView = wheelViewList.get(i);
            wheelView.setDividerColor(color);
            wheelView.setDividerType(dividerType);
        }
    }

    private void initWheelNum(int wheelNum,boolean cyc_scroll,float textSize) {
        setWeightSum(wheelNum);
        for (int i = 0; i < wheelNum; i++) {
            WheelView wheelView = new WheelView(getContext());
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight=1;
            wheelView.setTextColorCenter(Color.parseColor("#333333"));
            wheelView.setTextColorOut(Color.parseColor("#666666"));
            wheelView.setDividerColor(Color.parseColor("#333333"));
            wheelView.setCyclic(cyc_scroll);
            wheelView.setAdapter(new BaseWheelAdapterImp());
            if (textSize!=0)
            {
                wheelView.setTextSize(textSize);
            }
            addView(wheelView,layoutParams);
            wheelViewList.add(wheelView);
        }
    }


    public void setWheelViewList(int position,WheelAdapter  ...wheelAdapters)
    {
        if (position+wheelAdapters.length==wheelViewList.size())
        {
            for (int i = position; i < wheelViewList.size(); i++) {
                WheelAdapter wheelAdapter = wheelAdapters[i - position];
                WheelView wheelView = wheelViewList.get(i);
                if (wheelAdapter==null)
                {
                    wheelView.setAdapter(new BaseWheelAdapterImp());
                }
                else
                {
                    wheelView.setAdapter(wheelAdapter);
                }
            }
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
            }
            else
            {
                wheelView.setAdapter(new BaseWheelAdapterImp());
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
                WheelAdapter adapter = wheelView.getAdapter();
                if (adapter instanceof BaseWheelAdapter&&((BaseWheelAdapter) adapter).isData)
                {
                    list.add(wheelView.getCurrentItem());
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
