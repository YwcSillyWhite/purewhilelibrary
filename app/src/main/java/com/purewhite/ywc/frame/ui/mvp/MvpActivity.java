package com.purewhite.ywc.frame.ui.mvp;


import android.view.View;

import androidx.annotation.IdRes;
import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public abstract class MvpActivity<DB extends ViewDataBinding,P extends PresenterImp>
        extends BaseMvpActivity<DB,P> {

    @Override
    protected int getTitleBarId() {
        return  R.id.title_bar_layout;
    }
}
