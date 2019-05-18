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

    protected View onBarTitleView()
    {
        return null;
    }

    @Override
    protected void initBar() {
        super.initBar();
        final View view = onBarTitleView();
        BarUtils.obtianTitleConfig().setTitleBarPadding(view);
        BarUtils.obtianBarConfig().with(this)
                .setStatusBarHideFlag()
                .setStatusBarTextColorFlag(true)
                .build();
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

    //结束动画
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAnim();
    }

    @Override
    public void finish() {
        super.finish();
        finishAnim();
    }

    private int closeEnter=com.purewhite.ywc.purewhitelibrary.R.anim.activity_close_enter;
    private int closeExit=com.purewhite.ywc.purewhitelibrary.R.anim.activity_close_exit;

    public void setCloseAnim(int closeEnter,int closeExit) {
        this.closeEnter = closeEnter;
        this.closeExit = closeExit;
    }

    private boolean finishAnim=true;
    public void setFinishAnim(boolean finishAnim) {
        this.finishAnim = finishAnim;
    }
    private void finishAnim()
    {
        if (finishAnim)
        {
            overridePendingTransition(closeEnter,closeExit);
        }
    }
}
