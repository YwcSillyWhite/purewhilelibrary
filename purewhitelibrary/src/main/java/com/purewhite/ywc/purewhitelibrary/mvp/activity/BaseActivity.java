package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonCallBack;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonUtils;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxDisposableManager;


/**
 *
 * @author yuwenchao
 * @date 2018/11/3
 */

public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity implements PermissonCallBack {

    //是否横屏
    protected DB mDataBinding;
    //确宝同一个物品的页面唯一；
    private String activityId;


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeView();
        //设置横竖平
        setOrientation(true);
        //DataBinding绑定
        mDataBinding = DataBindingUtil.setContentView(this, getLayout());
        initView();
    }

    //设置横竖屏幕
    protected void setOrientation(boolean vertical)
    {
        try
        {
            setRequestedOrientation(vertical? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        catch (Exception e)
        {

        }
    }

    //去除标题栏
    protected void setFullScreen(boolean full)
    {
        //全屏
        if (full)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


    }

    protected void beforeView() {

    }

    //布局id
    protected abstract int getLayout();
    //初始化布局
    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxDisposableManager.getInstance().removeDis(this);

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
