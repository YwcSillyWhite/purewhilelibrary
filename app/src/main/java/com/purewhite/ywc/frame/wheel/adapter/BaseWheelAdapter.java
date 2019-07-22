package com.purewhite.ywc.frame.wheel.adapter;

import com.contrarywind.adapter.WheelAdapter;

import java.util.ArrayList;
import java.util.List;

public class BaseWheelAdapter implements WheelAdapter<String> {

    private List<String> list;

    public BaseWheelAdapter(List<String> list) {
        this.list = list==null?new ArrayList<String>():list;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public String getItem(int index) {
        return list.get(index);
    }

    @Override
    public int indexOf(String o) {
        return 0;
    }
}
