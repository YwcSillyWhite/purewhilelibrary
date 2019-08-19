package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonCallBack;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxDisposableManager;


/**
 *
 * @author yuwenchao
 * @date 2018/11/3
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{


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


    //点击事件
    public final void onClickView(View view) {
        if (ClickUtils.clickable(view))
        {
            onClickUtils(view);
        }
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.clickable(view))
        {
            onClickUtils(view);
        }
    }

    protected void onClickUtils(View view)
    {

    }


}
