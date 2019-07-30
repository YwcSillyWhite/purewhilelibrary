package com.purewhite.ywc.purewhitelibrary.mvp.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;


/**
 *
 * @author yuwenchao
 * @date 2018/11/14
 */

public abstract class BaseMvpFragment<D extends ViewDataBinding,P extends PresenterImp>
        extends BaseBindFragment<D> implements BaseUiView {

    protected P mPresenter;

    protected abstract P creartPresenter();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=creartPresenter();
        if (mPresenter!=null) {
            mPresenter.addView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null) {
            mPresenter.deleteView();
        }
    }


    @Nullable
    @Override
    public Context getContext() {
        return super.getContext()!=null?super.getContext(): AppUtils.getContext();
    }

    @Override
    public Fragment getFragment() {
        return this;
    }
}
