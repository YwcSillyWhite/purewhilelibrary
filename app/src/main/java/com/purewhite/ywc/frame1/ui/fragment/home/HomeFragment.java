package com.purewhite.ywc.frame1.ui.fragment.home;

import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.FragHomeBinding;
import com.purewhite.ywc.frame1.ui.adapter.HomePagerAdapter;
import com.purewhite.ywc.frame1.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

import java.util.Arrays;

public class HomeFragment extends MvpFragment<FragHomeBinding,PresenterImp>{


    @Override
    protected View onBarTitleView() {
        return mDataBinding.tabLayout;
    }

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_home;
    }


    @Override
    protected void initView() {
        String[] home_tag_title = getResources().getStringArray(R.array.home_tab_title);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), Arrays.asList(home_tag_title));
        mDataBinding.viewPager.setAdapter(homePagerAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
        mDataBinding.viewPager.setOffscreenPageLimit(home_tag_title.length);
    }
}
