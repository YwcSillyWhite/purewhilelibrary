package com.purewhite.ywc.purewhitelibrary.mvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonCallBack;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxDisposableManager;


/**
 *
 * @author yuwenchao
 * @date 2018/11/14
 */
public abstract class BaseFragment extends Fragment{

    //唯一加载
    private boolean soleLoad;
    private View view;
    @Override
    public Context getContext() {
        return super.getContext()!=null?super.getContext():AppUtils.getContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        final int layout = getLayout();
        if (layout!=0)
        {
            if (view==null) {
                view=setLayoutView(inflater,container,layout);
            }
            initView();
            afterView();
        }
        return view;
    }

    protected View setLayoutView(LayoutInflater inflater, @Nullable ViewGroup container,int layout)
    {
        return inflater.inflate(layout,container,false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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



    /**
     * 权限使用
     */
    private PermissonUtils permissonUtils;
    protected final void startPermisson(int requestCode,PermissonCallBack permissonCallBack, String ...permisson)
    {
        if (permissonUtils==null)
        {
            permissonUtils = PermissonUtils.with(this, permissonCallBack);
        }
        permissonUtils.startPermisson(requestCode,permisson);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissonUtils!=null)
        {
            permissonUtils.disposePermissions(requestCode,permissions,grantResults);
        }
    }
}
