package com.purewhite.ywc.purewhitelibrary.adapter.pagerview;


import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author yuwenchao
 */
public class ViewPagerAdapter extends PagerAdapter {

    private SparseArray<View> sparseArray;
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
        return view;
    }


    public void flush(SparseArray<View> sparseArray)
    {
        this.sparseArray=sparseArray;
        notifyDataSetChanged();
    }
}
