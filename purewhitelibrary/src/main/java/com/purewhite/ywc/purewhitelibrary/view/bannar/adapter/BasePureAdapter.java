package com.purewhite.ywc.purewhitelibrary.view.bannar.adapter;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePureAdapter<T> extends PagerAdapter {

    private List<T> list;
    private SparseArray<View> sparseArray;
    public BasePureAdapter(List<T> list) {
        this.list=handlerList(list);
        sparseArray=new SparseArray<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        T t = obtianT(position);
        View view = sparseArray.get(position);
        if (view==null)
        {
            view=obtianView(container,position,t);
            sparseArray.put(position,view);
        }
        container.addView(view);
        return view;
    }

    public abstract View obtianView(ViewGroup container, int position,T t);

    public T obtianT(int position)
    {
        if (position<getCount())
        {
            return list.get(position);
        }
        return null;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = sparseArray.get(position);
        if (view!=null)
        {
            container.removeView(view);
        }
    }

    public void flush(List<T> list)
    {
        this.list=handlerList(list);
        notifyDataSetChanged();
    }


    private List<T> handlerList(List<T> list)
    {
        if (list==null)
        {
            list=new ArrayList<>();
        }
        else
        {
            if (list.size()>1)
            {
                T endT = list.get(0);
                T startT = list.get(list.size() - 1);
                list.add(0,startT);
                list.add(endT);
            }
        }
        return list;
    }
}
