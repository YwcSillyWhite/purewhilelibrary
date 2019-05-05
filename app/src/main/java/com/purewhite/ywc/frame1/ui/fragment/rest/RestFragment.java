package com.purewhite.ywc.frame1.ui.fragment.rest;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.FragRestBinding;
import com.purewhite.ywc.frame1.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class RestFragment extends MvpFragment<FragRestBinding,PresenterImp>{
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_rest;
    }

    @Override
    protected void initView() {

    }
}
