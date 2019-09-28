package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview;


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

public abstract class BindAdapter<T> extends BaseLoadAdapter<T, BindHolder> {

    public BindAdapter()
    {
        this(null);
    }

    public BindAdapter(List<T> list) {
        super(list);
    }

    @Override
    protected BindHolder onDataCreateV(@NonNull ViewGroup parent, int layoutId) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false);
        BindHolder bindHolder = new BindHolder(binding);
        bindDataListener(bindHolder);
        return bindHolder;
    }
}
