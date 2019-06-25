package com.purewhite.ywc.purewhitelibrary.adapter.pagerview;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePagerAdapter<T> extends PagerAdapter {

    private List<T> list;
    private SparseArray<View> sparseArray;

    public BasePagerAdapter(List<T> list) {
        this.list = list==null?new ArrayList<T>():list;
        this.sparseArray=new SparseArray<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
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
        final T t = obtainT(position);
        View view=sparseArray.get(position);
        if (view==null)
        {
            view= obtainView(container,position, t);
        }
        sparseArray.put(position,view);
        container.addView(view);
        return view;
    }


    private T obtainT(int position)
    {
        if (position<getCount())
        {
            return list.get(position);
        }
        return null;
    }

    private List<T> obtainListT()
    {
        return list;
    }

    protected abstract View obtainView(ViewGroup container,int position,T t);




}
