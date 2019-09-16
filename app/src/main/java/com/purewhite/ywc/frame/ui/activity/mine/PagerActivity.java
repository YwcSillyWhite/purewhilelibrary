package com.purewhite.ywc.frame.ui.activity.mine;


import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityPagerBinding;
import com.purewhite.ywc.frame.ui.adapter.BannerPagerAdapter;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PagerActivity extends MvpActivity<ActivityPagerBinding, BasePresenter> {

    private Integer [] colors={0XFF199AFE,0XFFFF8080,0XFF00AB96,0XFF00ff00,0XFF001133};

    @Override
    protected int getLayout() {
        return R.layout.activity_pager;
    }

    @Override
    protected void initView() {
        String[] stringArray = getResources().getStringArray(R.array.view_pager);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < stringArray.length; i++) {
            list.add(stringArray[i]);
        }

        BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(Arrays.asList(stringArray));
        mDataBinding.viewPager.pureViewPager.setAdapter(bannerPagerAdapter);
        mDataBinding.viewPager.pureViewPager.initCurrentIemt();

        mDataBinding.viewPager.setLayoutView(list.size(),0);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mDataBinding.viewPager.pureViewPager.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mDataBinding.viewPager.pureViewPager.onPause();
    }
}
