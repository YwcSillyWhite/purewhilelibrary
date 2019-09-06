package com.purewhite.ywc.frame.ui.activity.mine;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityCustomMainBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.view.bottom.BottomLayout;
import com.purewhite.ywc.purewhitelibrary.view.bottom.BottomMenu;

public class CustomMainActivity extends MvpActivity<ActivityCustomMainBinding,PresenterImp> {

    private BottomLayout.OnBottomLayoutChageListener onBottomLayoutChageListener=new BottomLayout.OnBottomLayoutChageListener() {
        @Override
        public void onCheckChange(BottomMenu view) {

        }
    };


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
