package com.purewhite.ywc.frame1.ui.mvp;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonCallBack;
import com.purewhite.ywc.purewhitelibrary.mvp.fragment.MvpPureFragment;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public abstract class MvpFragment<DB extends ViewDataBinding,P extends PresenterImp>
        extends MvpPureFragment<DB,P> implements PermissonCallBack {

    protected View onBarTitleView()
    {
        return null;
    }

    @Override
    protected void initBar() {
        super.initBar();
        final View view = onBarTitleView();
        BarUtils.obtianTitleConfig().setTitleBarPadding(view);
    }


    @Override
    public void onPermissonSuccess(int requestCode) {

    }

    @Override
    public void onPermissonRepulse(int requestCode, String... permisssons) {

    }
}
