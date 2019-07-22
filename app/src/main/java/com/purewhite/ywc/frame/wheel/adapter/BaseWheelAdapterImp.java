package com.purewhite.ywc.frame.wheel.adapter;

import java.util.List;

public class BaseWheelAdapterImp extends BaseWheelAdapter<String>{

    public BaseWheelAdapterImp() {
        super(null);
    }

    public BaseWheelAdapterImp(List<String> list) {
        super(list);
    }

    @Override
    public String getItem(int index) {
        return list.get(index);
    }


}
