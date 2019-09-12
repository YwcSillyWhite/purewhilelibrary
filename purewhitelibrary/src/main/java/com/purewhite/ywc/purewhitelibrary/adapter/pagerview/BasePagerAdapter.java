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

    private List<T> list=new ArrayList<>();
    private SparseArray<View> sparseArray;
    private OnPagerItemListener onItemListener;
    //item是否可以点击
    private boolean isItemClick=true;
    public void setOnItemListener(OnPagerItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public BasePagerAdapter(List<T> list) {
        this.list.addAll(list);
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
        View view = sparseArray.get(viewPosition(position));
        if (view!=null)
        {
            container.removeView(view);
        }
    }




    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int viewPosition = viewPosition(position);
        int dataPosition = dataPosition(position);
        final T t = obtainT(dataPosition);
        View view=sparseArray.get(viewPosition);
        if (view==null)
        {
            view = LayoutInflater.from(container.getContext()).inflate(getLayoutId(dataPosition), container, false);
            if (view!=null)
            {
                onData(view,dataPosition,t);
            }
            sparseArray.put(viewPosition,view);
        }

        //是否存在父类。存在删除
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent!=null)
        {
            parent.removeView(view);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemListener!=null&&isItemClick&&ClickUtils.clickable(view))
                {
                    onItemListener.onClick(BasePagerAdapter.this,view,dataPosition,true);
                }
            }
        });
        container.addView(view);
        return view;
    }


    protected T obtainT(int position) {
        if (position<getCount())
        {
            return list.get(position);
        }
        return null;
    }



    protected List<T> obtainData() {
        return list;
    }

    public int dataPosition(int position) {
        return position;
    }

    protected int viewPosition(int position) {
        return position;
    }

    //获取布局id
    protected abstract int getLayoutId(int position);

    protected abstract void onData(View view,int position,T t);

}
