package com.purewhite.ywc.purewhitelibrary.mvp.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

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
}
