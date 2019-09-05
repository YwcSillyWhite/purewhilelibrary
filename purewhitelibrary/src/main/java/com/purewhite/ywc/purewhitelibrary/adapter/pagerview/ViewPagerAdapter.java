package com.purewhite.ywc.purewhitelibrary.adapter.pagerview;


import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnPagerItemListener;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;

/**
 * view 的 pager adapter
 * @author yuwenchao
 */
public class ViewPagerAdapter extends PagerAdapter {

    private SparseArray<View> sparseArray;
    private OnPagerItemListener onItemListener;
    private boolean isItemClick=true;
    public void setOnItemListener(OnPagerItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public ViewPagerAdapter(SparseArray<View> sparseArray) {
        this.sparseArray = sparseArray;
    }

    @Override
    public int getCount() {
        return sparseArray.size();
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
        View view = sparseArray.get(position);
        if (view!=null)
        {
            container.addView(view);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemListener!=null&&isItemClick&& ClickUtils.clickable(view))
                {
                    onItemListener.onClick(ViewPagerAdapter.this,view,position,true);
                }
            }
        });
        return view;
    }


    public void flush(SparseArray<View> sparseArray)
    {
        this.sparseArray=sparseArray;
        notifyDataSetChanged();
    }
}
