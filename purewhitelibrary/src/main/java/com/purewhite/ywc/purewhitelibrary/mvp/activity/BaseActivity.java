package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

        //设置横竖平
        setOrientation();
        beforeView();
        //布局id不为null ，那么就进行databinding
        final int layout = getLayout();
        if (layout!=0)
        {
            setLayoutView(layout);
            initView();
        }
        afterView();
        initRquest();
    }

    //是否竖屏
    protected boolean isVertical()
    {
        return true;
    }

    //设置横竖屏幕
    private void setOrientation()
    {
        //android 8.0之后如果屏幕满屏透明是不能设置屏幕方向
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            try
            {
                setRequestedOrientation(isVertical()? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            catch (Exception e)
            {

            }
        }
        else
        {
            setRequestedOrientation(isVertical()? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }


    //初始化之前
    protected void beforeView() {

    }



    protected void setLayoutView(int layoutId)
    {
        setContentView(getLayout());
    }


    //初始化之后
    protected void afterView()
    {

    }

    //网络请求
    protected void initRquest()
    {

    }

    //布局id
    @LayoutRes
    protected abstract int getLayout();
    //初始化布局
    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
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
