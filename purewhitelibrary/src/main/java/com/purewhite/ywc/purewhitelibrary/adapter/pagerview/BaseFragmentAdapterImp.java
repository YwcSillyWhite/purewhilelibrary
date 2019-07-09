package com.purewhite.ywc.purewhitelibrary.adapter.pagerview;

import android.util.SparseArray;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 * @author yuwenchao
 */
public  class BaseFragmentAdapterImp extends FragmentPagerAdapter {

    private SparseArray<Fragment> sparseArray;

    public BaseFragmentAdapterImp(FragmentManager fm, SparseArray<Fragment> sparseArray) {
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
