package com.purewhite.ywc.frame.ui.adapter;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.purewhite.ywc.frame.config.TagUtils;
import com.purewhite.ywc.frame.ui.fragment.home.child.HomeChildFragment;
import com.purewhite.ywc.purewhitelibrary.adapter.pagerview.BaseFragmentAdapter;
import com.purewhite.ywc.purewhitelibrary.config.bundle.BundleUtils;

import java.util.List;

public class HomePagerAdapter extends BaseFragmentAdapter<String> {

    public HomePagerAdapter(FragmentManager fm, List<String> data) {
        super(fm, data);
    }

    @Override
    protected Fragment getFragment(int position) {
        HomeChildFragment homeChildFragment=new HomeChildFragment();
        Bundle build = BundleUtils.buidler()
                .put(TagUtils.home_child_tag, position)
                .put(TagUtils.home_child_title, mDatas.get(position))
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
