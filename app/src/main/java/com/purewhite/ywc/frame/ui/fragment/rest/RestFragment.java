package com.purewhite.ywc.frame.ui.fragment.rest;


import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.FragRestBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class RestFragment extends MvpFragment<FragRestBinding,PresenterImp> {


    @Override
    protected int getLayout() {
        return R.layout.frag_rest;
    }


    @Override
    protected void beforView() {
    }

    @Override
    protected void initView() {


    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
