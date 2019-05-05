package com.purewhite.ywc.purewhitelibrary.mvp.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;


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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getLayout()!=0)
        {
            if (mDataBinding==null)
                mDataBinding=DataBindingUtil.inflate(inflater,getLayout(),container,false);
            return mDataBinding.getRoot();
        }
        else {
            return null;
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        soleLoad=true;
        if (getUserVisibleHint())
        {
            decideSoleLoad();
        }
        showLoad();
    }


    //布局
    protected abstract int getLayout();
    //初始化布局
    protected abstract void initView();
    //判断是否加载

    private void decideSoleLoad() {
        if (soleLoad)
        {
            soleLoad=false;
            soleLoad();
        }
    }


    //唯一加载
    protected void soleLoad()
    {

    }

    protected void showLoad()
    {

    }



    //当前fragment是否显示
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            decideSoleLoad();
        }
    }



    /**
     * 第一次加入进去是不加载的，只有在隐藏和显示的时候才会调用这个方法
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden)
        {
            showLoad();
        }
    }



}
