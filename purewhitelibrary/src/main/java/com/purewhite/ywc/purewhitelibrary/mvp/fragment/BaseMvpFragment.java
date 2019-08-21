package com.purewhite.ywc.purewhitelibrary.mvp.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 *
 * @author yuwenchao
 * @date 2018/11/14
 */

public abstract class BaseMvpFragment<D extends ViewDataBinding,P extends PresenterImp>
        extends BaseBindFragment<D> implements BaseUiView {

    protected P mPresenter;
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
