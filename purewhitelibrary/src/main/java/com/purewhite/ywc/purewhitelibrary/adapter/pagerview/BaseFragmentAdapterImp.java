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
public abstract class BaseFragmentAdapterImp extends FragmentPagerAdapter {

    private SparseArray<Fragment> sparseArray;

    public BaseFragmentAdapterImp(FragmentManager fm,SparseArray<Fragment> sparseArray) {
        super(fm);
        this.sparseArray=new SparseArray<>();
    }

    @Override
    public Fragment getItem(int position) {
        return sparseArray.get(position);
    }

    protected abstract Fragment getFragment(int position);

    @Override
    public int getCount() {
        return sparseArray.size();
    }
}
