package com.purewhite.ywc.frame1.ui.activity.mine;

import android.view.View;

import com.purewhite.ywc.frame1.databinding.ActivityCameraBinding;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class CameraActivity extends MvpActivity<ActivityCameraBinding,PresenterImp> {
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected View onBarTitleView() {
        return null;
    }


    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }
}
