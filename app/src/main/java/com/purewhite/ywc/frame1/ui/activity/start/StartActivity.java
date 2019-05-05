package com.purewhite.ywc.frame1.ui.activity.start;


import android.os.Handler;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.ActivityStartBinding;
import com.purewhite.ywc.frame1.ui.activity.main.MainActivity;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.app.ActivityUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class StartActivity extends MvpActivity<ActivityStartBinding,PresenterImp> {
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityUtils.startActivity(MainActivity.class);
                ActivityUtils.finish(StartActivity.this);
            }
        },500);
    }
}
