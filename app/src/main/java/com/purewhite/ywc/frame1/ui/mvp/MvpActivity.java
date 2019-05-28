package com.purewhite.ywc.frame1.ui.mvp;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.View;

import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonCallBack;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.MvpPureActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public abstract class MvpActivity<DB extends ViewDataBinding,P extends PresenterImp>
        extends MvpPureActivity<DB,P> implements PermissonCallBack {


    @Override
    protected void beforeView() {
        super.beforeView();
        initBar();
    }

    //设置状态栏
    protected void initBar()
    {
        BarUtils.obtianBarConfig().with(this)
                .setStatusBarHideFlag()
                .setStatusBarTextColorFlag(true)
                .build();
    }

    @Override
    protected void afterView() {
        super.afterView();
        BarUtils.obtianTitleConfig().setTitleBarPadding(onBarTitleView());
    }

    //延伸布局到状态栏里
    protected View onBarTitleView()
    {
        return null;
    }



    //开启默认动画
    @Override
    protected boolean isFinishAnim() {
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissonUtils.permissionsResult(this,requestCode,permissions,grantResults,this);
    }

    //权限开始失败
    @Override
    public void onPermissonRepulse(int requestCode, String... permisssons) {

    }

    //权限开启成功
    @Override
    public void onPermissonSuccess(int requestCode) {

    }






}
