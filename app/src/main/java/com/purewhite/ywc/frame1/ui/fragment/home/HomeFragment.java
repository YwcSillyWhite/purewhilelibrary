package com.purewhite.ywc.frame1.ui.fragment.home;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.FragHomeBinding;
import com.purewhite.ywc.frame1.ui.mvp.MvpFragment;

public class HomeFragment extends MvpFragment<FragHomeBinding,HomePresenter> implements HomeContract.UiView {
    @Override
    protected HomePresenter creartPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_home;
    }

    @Override
    protected void initView() {
        mPresenter.obtianShop();
    }
}
