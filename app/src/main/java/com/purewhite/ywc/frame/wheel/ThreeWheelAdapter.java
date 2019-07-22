package com.purewhite.ywc.frame.wheel;

import com.purewhite.ywc.frame.wheel.adapter.BaseWheelAdapter;

import java.util.List;

public class ThreeWheelAdapter extends BaseWheelAdapter<WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX> {

    public ThreeWheelAdapter(List<WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX> list) {
        super(list);
    }

    @Override
    public String obtianItem(WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX childrenBeanX, int index) {
        return childrenBeanX.getName();
    }
}
