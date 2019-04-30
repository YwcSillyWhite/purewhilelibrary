package com.purewhite.ywc.purewhitelibrary.adapter.pagerview;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter {

    private List<T> data;
    private SparseArray<View> sparseArray;

    public BasePagerAdapter() {
        this(null);
    }

    public BasePagerAdapter(List<T> data) {
        this.data=data==null?new ArrayList<T>():data;
        sparseArray=new SparseArray<>();
    }

    protected int getDataCount()
    {
        return data.size();
    }

    @Override
    public int getCount() {
        return getDataCount();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = sparseArray.get(position);
        if (view!=null)
        {
            container.removeView(view);
        }

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = sparseArray.get(position);
        if (view==null)
        {
            view= obtainView(position);
            sparseArray.put(position,view);
        }
        container.addView(view);
        return view;
    }

    protected abstract View obtainView(int position);

    protected T obtainT(int position)
    {
        position=getPosition(position);
        if (getDataCount()>position)
        {
            return data.get(position);
        }
        return null;

    }


    //给无限循环写的方法
    protected int getPosition(int position)
    {
        return position;
    }


    public void flush(List<T> list)
    {
        data=list!=null?list:new ArrayList<T>();
        sparseArray.clear();
        notifyDataSetChanged();
    }
}
