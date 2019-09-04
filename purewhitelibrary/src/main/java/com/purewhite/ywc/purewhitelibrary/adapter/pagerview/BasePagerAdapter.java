package com.purewhite.ywc.purewhitelibrary.adapter.pagerview;


import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnPagerItemListener;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePagerAdapter<T> extends PagerAdapter {

    private List<T> list;
    private SparseArray<View> sparseArray;
    private OnPagerItemListener onItemListener;
    private boolean isItemClick=true;
    public void setOnItemListener(OnPagerItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

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
            view= onItemView(container,position, t);
            sparseArray.put(position,view);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemListener!=null&&isItemClick&&ClickUtils.clickable(view))
                {
                    onItemListener.onClick(BasePagerAdapter.this,view,position,true);
                }
            }
        });
        container.addView(view);
        return view;
    }


    protected T obtainT(int position)
    {
        if (position<getCount())
        {
            return list.get(position);
        }
        return null;
    }

    protected List<T> obtainData()
    {
        return list;
    }


    private View onItemView(ViewGroup container,int position,T t) {
        View itemView = LayoutInflater.from(container.getContext()).inflate(getLayoutId(position), container, false);
        if (itemView!=null)
        {
            onData(itemView,position,t);
        }
        return itemView;
    }

    //获取布局id
    protected abstract int getLayoutId(int position);

    protected abstract void onData(View view,int position,T t);

}
