package com.purewhite.ywc.purewhitelibrary.adapter.pagerview;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author yuwenchao
 */
public class BasePagerAdapterImp extends PagerAdapter {

    private SparseArray<View> sparseArray;
    public BasePagerAdapterImp(SparseArray<View> sparseArray) {
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
