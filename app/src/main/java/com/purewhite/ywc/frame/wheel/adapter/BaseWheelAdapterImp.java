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
    public String obtianItem(String s, int index) {
        return s;
    }


}
