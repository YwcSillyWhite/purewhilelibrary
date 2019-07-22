package com.purewhite.ywc.frame.wheel;

import com.purewhite.ywc.frame.wheel.adapter.BaseWheelAdapter;

import java.util.List;

public class TwoWheelAdapter extends BaseWheelAdapter<WheelBean.DataBean.ChildrenBeanXX> {

    public TwoWheelAdapter(List<WheelBean.DataBean.ChildrenBeanXX> list) {
        super(list);
    }

    @Override
    public String obtianItem(WheelBean.DataBean.ChildrenBeanXX childrenBeanXX, int index) {
        return childrenBeanXX.getName();
    }
}
