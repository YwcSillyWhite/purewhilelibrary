package com.purewhite.ywc.purewhitelibrary.mvp.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.config.pattern.PatternUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.IBaseView;


/**
 *
 * @author yuwenchao
 * @date 2018/11/14
 */

public abstract class BaseMvpFragment<D extends ViewDataBinding,P extends BasePresenter>
        extends BaseBindFragment<D> implements IBaseView {

    protected P mPresenter;
    //创建PresenterImp对象
    protected P creartPresenter() {
        if (isPresenter()) {
            Object object = PatternUtils.getT(this, positionPresenter());
            if (object instanceof BasePresenter) {
                return ((P) object);
            } else {
                throw new IllegalArgumentException("Two pattern not BasePresenter");
            }
        }
        return null;
    }
    //第几个范型是Presenter
    protected int positionPresenter() {
        return 1;
    }
    //如果不使用presenter的时候，可以使用false
    protected boolean isPresenter() {
        return true;
    }

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
