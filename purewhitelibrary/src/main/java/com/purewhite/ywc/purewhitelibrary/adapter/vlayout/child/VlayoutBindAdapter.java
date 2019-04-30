package com.purewhite.ywc.purewhitelibrary.adapter.vlayout.child;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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
public abstract class VlayoutBindAdapter<T> extends VlayoutBaseAdapter<T,BindHolder>{

    private SparseIntArray sparseIntArray;
    public VlayoutBindAdapter()
    {
        super(null);
    }

    public VlayoutBindAdapter(List<T> list) {
        super(list);
    }

    protected void addLayout(int layoutId)
    {
        addLayout(0,layoutId);
    }

    protected void addLayout(int viewType,int layoutId)
    {
        if (sparseIntArray==null)
        {
            sparseIntArray=new SparseIntArray();
        }
        sparseIntArray.put(viewType,layoutId);
    }


    @Override
    protected BindHolder onCreateData(ViewGroup parent, int viewType) {
        if (getLayout(viewType)!=-1)
        {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    getLayout(viewType), parent, false);
            return new BindHolder(binding);
        }
        return null;
    }



    private int getLayout(int viewType)
    {
        if (sparseIntArray!=null) {
            return sparseIntArray.get(viewType,-1);
        }
        return -1;
    }

}
