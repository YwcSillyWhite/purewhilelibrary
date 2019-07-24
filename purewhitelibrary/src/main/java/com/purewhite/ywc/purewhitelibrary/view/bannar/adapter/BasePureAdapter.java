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
    private List<T> handerList(List<T> list)
    {
        if (list==null)
        {
            list=new ArrayList<>();
        }
        return list;
    }
    private SparseArray<View> sparseArray;
    public BasePureAdapter(List<T> list) {
        this.list = handerList(list);
        sparseArray=new SparseArray<>();
    }

    //list真实长度
    public int getRealCount()
    {
        return list.size();
    }

    @Override
    public int getCount() {
        return getRealCount()>1?Integer.MAX_VALUE:getRealCount();
    }

    //当前真实position
    public int getRealPosition(int position)
    {
        final int realCount = getRealCount();
        if (realCount>0)
        {
            return position%getRealCount();
        }
        return 0;
    }

    public int initPosition()
    {
        int realCount = getRealCount();
        if (realCount<=1)
        {
            return realCount-1;
        }
        else
        {
            int centerPosition = getCount() / 2;
            int realPosition = centerPosition % realCount;
            return centerPosition-realPosition;
        }
    }

    public T obtianT(int position)
    {
        if (position<getCount())
        {
            return list.get(position);
        }
        return null;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @NonNull
    @Override
    public final Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = getRealPosition(position);
        return instantiateItemReal(container,realPosition);
    }

    public final Object instantiateItemReal(@NonNull ViewGroup container, int position)
    {
        T t = obtianT(position);
        View view = sparseArray.get(position);
        if (view==null)
        {
            view=obtianView(container,position,t);
            sparseArray.put(position,view);
        }
        //重复复用所以要先移除view
        container.removeView(view);
        container.addView(view);
        return view;
    }

    public abstract View obtianView(ViewGroup container, int position, T t);


    @Override
    public final void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }



    public void flush(List<T> list)
    {
        this.list=handerList(list);
        sparseArray.clear();
        notifyDataSetChanged();
    }
}
