package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;

import java.util.List;

/**
 *采用这个类使用多布局一定要重写getDataType()这个方法
 * @author yuwenchao
 * @date 2018/11/17
 * 使用数据里面的
 */

public abstract class BindAdapter<T> extends BaseAdapter<T,BindHolder>{

    private SparseIntArray sparseIntArray;

    public BindAdapter()
    {
        this(null);
    }

    public BindAdapter(List<T> list) {
        super(list);
        sparseIntArray=new SparseIntArray();
    }


    //添加布局
    protected final void addLayout(int viewType,@NonNull@LayoutRes int layoutId)
    {
        if (sparseIntArray==null)
        {
            sparseIntArray=new SparseIntArray();
        }
        sparseIntArray.put(viewType,layoutId);
    }

    protected final void addLayout(int layout)
    {
        addLayout(0,layout);
    }


    @Override
    protected final BindHolder onCreateData(ViewGroup parent, int viewType) {
        int layoutId = getLayout(viewType);
        if (layoutId!=0)
        {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                    , layoutId, parent, false);
            return new BindHolder(binding);
        }
        return null;
    }



    protected int getLayout(int viewType)
    {
        return sparseIntArray.get(viewType,0);
    }

}
