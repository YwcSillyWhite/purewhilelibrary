package com.purewhite.ywc.frame.ui.mvp;


import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.fragment.BaseMvpFragment;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public abstract class MvpFragment<DB extends ViewDataBinding,P extends PresenterImp>
        extends BaseMvpFragment<DB,P> {


    @Override
    protected int getTitleBarId() {
        return R.id.title_bar_layout;
    }
}
