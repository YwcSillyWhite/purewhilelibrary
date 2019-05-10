package com.purewhite.ywc.purewhitelibrary.adapter.pagerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;


/**
 * @author yuwenchao
 */
public  class BaseFragmentAdapterImp extends FragmentPagerAdapter {

    private SparseArray<Fragment> sparseArray;

    public BaseFragmentAdapterImp(FragmentManager fm,SparseArray<Fragment> sparseArray) {
        super(fm);
        this.sparseArray=sparseArray;
    }

    @Override
    public Fragment getItem(int position) {
        return sparseArray.get(position);
    }

    @Override
    public int getCount() {
        return sparseArray.size();
    }
}
