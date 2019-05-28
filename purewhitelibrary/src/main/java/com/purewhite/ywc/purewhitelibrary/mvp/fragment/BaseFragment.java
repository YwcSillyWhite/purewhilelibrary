package com.purewhite.ywc.purewhitelibrary.mvp.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxDisposableManager;


/**
 *
 * @author yuwenchao
 * @date 2018/11/14
 */
public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment{

    protected DB mDataBinding;
    //唯一加载
    private boolean soleLoad;

    @Override
    public Context getContext() {
        return super.getContext()!=null?super.getContext():AppUtils.getContext();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        beforView();
        if (getLayout()!=0)
        {
            if (mDataBinding==null)
                mDataBinding=DataBindingUtil.inflate(inflater,getLayout(),container,false);
            return mDataBinding.getRoot();
        }
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        afterView();
        soleLoad=true;
        //用户是否可见
        decideSoleLoad(getUserVisibleHint());
    }

    //view初始化之前
    protected void beforView()
    {

    }

    //view初始化之后
    protected void afterView()
    {

    }


    //布局
    @LayoutRes
    protected abstract int getLayout();
    //初始化布局
    protected abstract void initView();

    //判断是否加载
    private void decideSoleLoad(boolean isShow) {
        if (isShow&&soleLoad)
        {
            soleLoad=false;
            soleLoad();
        }
    }


    //唯一加载
    protected void soleLoad()
    {

    }

    //当前fragment是否显示
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        decideSoleLoad(isVisibleToUser);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        RxDisposableManager.getInstance().removeDis(this);
        OkHttpUtils.newInstance().cancleTag(this);
    }
}
