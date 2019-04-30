package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseView;


/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 */

public abstract class MvpActivity<DB extends ViewDataBinding,P extends PresenterImp>
        extends BaseActivity<DB> implements BaseView {

    protected P mPresenter;
    @Override
    public Context getContext() {
        return this;
    }

    //创建PresenterImp对象
    protected abstract P creartPresenter();

    @Override
    protected void beforeView() {
        super.beforeView();
        mPresenter= creartPresenter();
        if (mPresenter!=null) {
            mPresenter.addView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null) {
            mPresenter.deleteView();
        }
    }
}
