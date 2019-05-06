package com.purewhite.ywc.frame1.ui.fragment.home.child;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.FragHomeChildBinding;
import com.purewhite.ywc.frame1.ui.mvp.MvpFragment;

public class HomeChildFragment extends MvpFragment<FragHomeChildBinding,HomeChildPresenter> implements HomeChildContract.UiView {

    @Override
    protected HomeChildPresenter creartPresenter() {
        return new HomeChildPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_home_child;
    }

    @Override
    protected void initView() {

    }

}
