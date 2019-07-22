package com.purewhite.ywc.frame.wheel;

import com.purewhite.ywc.frame.wheel.adapter.BaseWheelAdapter;

import java.util.List;

public class OneWheelAdapter extends BaseWheelAdapter<WheelBean.DataBean> {

    public OneWheelAdapter(List<WheelBean.DataBean> list) {
        super(list);
    }

    @Override
    public String obtianItem(WheelBean.DataBean dataBean, int index) {
        return dataBean.getName();
    }
}
