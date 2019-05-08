package com.purewhite.ywc.frame1.ui.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.purewhite.ywc.frame1.config.TagUtils;
import com.purewhite.ywc.frame1.ui.fragment.home.child.HomeChildFragment;
import com.purewhite.ywc.purewhitelibrary.adapter.pagerview.BaseFragmentAdapter;
import com.purewhite.ywc.purewhitelibrary.config.BundleUtils;

import java.util.List;

public class HomePagerAdapter extends BaseFragmentAdapter<String> {

    public HomePagerAdapter(FragmentManager fm, List<String> data) {
        super(fm, data);
    }

    @Override
    protected Fragment getFragment(int position) {
        HomeChildFragment homeChildFragment=new HomeChildFragment();
        Bundle build = BundleUtils.newInstance()
                .putInt(TagUtils.home_child_tag, position)
                .putString(TagUtils.home_child_title, mDatas.get(position))
                .build();
        homeChildFragment.setArguments(build);
        return homeChildFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position);
    }
}
