package com.purewhite.ywc.frame1.ui.fragment.mine;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.FragMineBinding;
import com.purewhite.ywc.frame1.ui.mvp.MvpFragment;

public class MineFragment extends MvpFragment<FragMineBinding,MinePresenter> {
    @Override
    protected MinePresenter creartPresenter() {
        return new MinePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_mine;
    }

    @Override
    protected void initView() {

    }
}
