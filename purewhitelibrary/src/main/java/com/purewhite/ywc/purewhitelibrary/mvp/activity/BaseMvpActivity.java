package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;


/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 */

public abstract class BaseMvpActivity<D extends ViewDataBinding,P extends PresenterImp>
        extends BaseBindActivity<D> implements BaseUiView {

    protected P mPresenter;

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Fragment getFragment() {
        return null;
    }

    //创建PresenterImp对象
    private P creartPresenter()
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass!=null&&genericSuperclass instanceof ParameterizedType)
        {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] types = parameterizedType.getActualTypeArguments();
            final int positionPresenter = positionPresenter();
            if (types!=null&&types.length>positionPresenter)
            {
                Type type = types[positionPresenter];
                try {
                    return ((Class<P>) type).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //第几个范型是Presenter
    protected int positionPresenter()
    {
        return 1;
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
}
