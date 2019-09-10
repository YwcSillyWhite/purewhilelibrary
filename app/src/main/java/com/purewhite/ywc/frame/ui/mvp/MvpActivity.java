package com.purewhite.ywc.frame.ui.mvp;


import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePurePresenter;

public abstract class MvpActivity<DB extends ViewDataBinding,P extends BasePurePresenter>
        extends BaseMvpActivity<DB,P> {

    @Override
    protected int getTitleBarId() {
        return  R.id.title_bar_layout;
    }
}
