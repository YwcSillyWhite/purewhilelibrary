package com.purewhite.ywc.frame1.ui.mvp;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final View view = onBarTitleView();
        BarUtils.obtianTitleConfig().setTitleBaeHeight(view);
    }



    @Override
    public void onPermissonSuccess(int requestCode) {

    }

    @Override
    public void onPermissonRepulse(int requestCode, String... permisssons) {

    }
}
