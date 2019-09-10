package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.config.pattern.PatternUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePurePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.IBasePureView;


/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 */

public abstract class BaseMvpActivity<D extends ViewDataBinding,P extends BasePurePresenter>
        extends BaseBindActivity<D> implements IBasePureView {

    protected P mPresenter;
    //创建PresenterImp对象
    protected P creartPresenter() {
        if (isPresenter()) {
            Object object = PatternUtils.getT(this, positionPresenter());
            if (object instanceof BasePurePresenter) {
                return ((P) object);
            } else {
                throw new IllegalArgumentException("Two pattern not BasePurePresenter");
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


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Fragment getFragment() {
        return null;
    }

}
