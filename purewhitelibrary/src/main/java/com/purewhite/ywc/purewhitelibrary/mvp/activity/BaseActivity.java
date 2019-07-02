package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.config.AdaptiveUtils;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonCallBack;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxDisposableManager;


/**
 *
 * @author yuwenchao
 * @date 2018/11/3
 */

public abstract class BaseActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局之前
        beforeView();
        //初始化设置布局
        final int layoutId = getLayout();
        if (layoutId!=0)
        {
            initSetView(layoutId);
            //初始化布局
            initView();
            //设置布局之后
            afterView();
        }
        //网络请求
        initRquest();
    }


    //初始化之前
    protected void beforeView() {
        //设置横竖平
        initOrientation();
        //适配
        initAdaptive();

    }

    //是否竖屏
    protected boolean isVertical()
    {
        return true;
    }

    //初始化横竖屏幕
    private void initOrientation() {
        //android 8.0之后如果屏幕满屏透明是不能设置屏幕方向
        try
        {
            setRequestedOrientation(isVertical()? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        catch (Exception e)
        {

        }
    }
    //能否适配
    protected boolean isAdaptive()
    {
        return true;
    }
    //初始化适配
    private void initAdaptive() {
        if (isAdaptive())
        {
            AdaptiveUtils.adaptiveWidth(this, AppUtils.getContext(),AppUtils.adaptiveWightDp);
        }
    }

    //设置布局
    protected void initSetView(int layoutId)
    {
        setContentView(layoutId);
    }

    //布局id
    @LayoutRes
    protected abstract int getLayout();
    //初始化布局
    protected abstract void initView();



    //初始化之后
    protected void afterView()
    {

    }

    //网络请求
    protected void initRquest()
    {

    }






    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消网络请求
        RxDisposableManager.getInstance().removeDis(this);
        OkHttpUtils.newInstance().cancleTag(this);
    }








    /**
     * 权限使用
     */
    private PermissonUtils permissonUtils;
    protected void startPermisson(int requestCode,PermissonCallBack permissonCallBack, String ...permisson)
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
