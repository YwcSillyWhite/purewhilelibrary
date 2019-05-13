package com.purewhite.ywc.frame1.ui.activity.mine;

import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.ActivityCustomMainBinding;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.view.bottom.BottomLayout;

public class CustomMainActivity extends MvpActivity<ActivityCustomMainBinding,PresenterImp> {

    private BottomLayout.OnBottomLayoutChageListener onBottomLayoutChageListener=new BottomLayout.OnBottomLayoutChageListener() {
        @Override
        public void onCheckChange(View view) {

        }
    };

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_custom_main;
    }

    @Override
    protected void initView() {
        mDataBinding.bottomLayout.addOnBottomLayoutChageListener(onBottomLayoutChageListener);
        mDataBinding.bottomMe.setMessageNum(87);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataBinding.bottomLayout.removeOnBottomLayoutChageListener();

    }
}
