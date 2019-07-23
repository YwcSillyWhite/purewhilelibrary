package com.purewhite.ywc.frame.wheel;

import com.purewhite.ywc.frame.wheel.adapter.BaseWheelAdapter;
import com.purewhite.ywc.frame.wheel.bean.WheelBean;

import java.util.List;

public class FourWheelAdapter extends BaseWheelAdapter<WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX.ChildrenBean> {

    public FourWheelAdapter(List<WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX.ChildrenBean> list) {
        super(list);
    }

    @Override
    public String obtianItem(WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX.ChildrenBean childrenBean, int index) {
        return childrenBean.getName();
    }
}
