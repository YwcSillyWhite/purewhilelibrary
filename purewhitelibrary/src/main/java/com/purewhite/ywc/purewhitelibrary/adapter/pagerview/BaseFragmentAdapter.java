package com.purewhite.ywc.purewhitelibrary.adapter.pagerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public abstract class BaseFragmentAdapter<T> extends FragmentPagerAdapter {

    protected List<T> mDatas;
    private SparseArray<Fragment> sparseArray;

    public BaseFragmentAdapter(FragmentManager fm) {
        this(fm,new ArrayList<T>());
    }

    public BaseFragmentAdapter(FragmentManager fm, List<T> data) {
        super(fm);
        this.mDatas=data;
        sparseArray=new SparseArray<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = sparseArray.get(position);
        if (fragment==null)
        {
            fragment=getFragment(position);
            sparseArray.put(position,fragment);
        }
        return fragment;
    }

    protected abstract Fragment getFragment(int position);

    @Override
    public int getCount() {
        return mDatas!=null?mDatas.size():0;
    }


    public void flush(List<T> list)
    {
        mDatas=list!=null?list:new ArrayList<T>();
        sparseArray.clear();
        notifyDataSetChanged();
    }

}
