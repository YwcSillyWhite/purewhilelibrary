package com.purewhite.ywc.frame.wheel.adapter;

import com.contrarywind.adapter.WheelAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract  class BaseWheelAdapter<T> implements WheelAdapter<String> {

    protected List<T> list;

    public List<T> obtainList()
    {
        return list;
    }
    public boolean isData;
    public BaseWheelAdapter(List<T> list) {
        this.list= list==null? new ArrayList<T>() :list;
        isData=this.list.size()>0;
    }

    @Override
    public int getItemsCount() {
        return isData?list.size():1;
    }

    @Override
    public final String getItem(int index) {
        return isData?obtianItem(list.get(index),index):" ";
    }

    public abstract String obtianItem(T t,int index);

    @Override
    public int indexOf(String o) {
        return 0;
    }

}
