package com.purewhite.ywc.purewhitelibrary.adapter.vlayout.child;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;

import java.util.List;

/**
 *采用这个类使用多布局一定要重写getDataType()这个方法
 * @author yuwenchao
 * @date 2018/11/17
 * 使用数据里面的
 */
public abstract class VlayoutBindAdapter<T> extends VlayoutBaseAdapter<T,BindHolder>{

    public VlayoutBindAdapter()
    {
        super(null);
    }

    public VlayoutBindAdapter(List<T> list) {
        super(list);
    }


    @Override
    protected BindHolder onDataCreateViewHolder(@NonNull ViewGroup parent, int viewType, int layoutIds) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , layoutIds, parent, false);
        BindHolder bindHolder = new BindHolder(binding);
        bindDataListener(bindHolder);
        return bindHolder;
    }




}
