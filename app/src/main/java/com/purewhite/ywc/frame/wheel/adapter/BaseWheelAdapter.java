package com.purewhite.ywc.frame.wheel.adapter;

import com.contrarywind.adapter.WheelAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseWheelAdapter<T> implements WheelAdapter<String> {

    protected List<T> list;

    public BaseWheelAdapter(List<T> list) {
        this.list= list==null? new ArrayList<T>() :list;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public int indexOf(String o) {
        return 0;
    }

}