package com.purewhite.ywc.frame.ui.activity.mine.dialog;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityDialogHomeBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;

public class DialogHomeActiivty extends MvpActivity<ActivityDialogHomeBinding, BasePresenter> {


    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.bottomsheetUtils:
                    break;
                case R.id.dialogUtils:
                    skipActivity(DialogActivity.class);
                    break;
                case R.id.popupwindowUtils:
                    break;
                case R.id.left_img:
                    finish();
                    break;

            }
        }
    };


    @Override
    protected int getLayout() {
        return R.layout.activity_dialog_home;
    }

    @Override
    protected void initView() {
        mDataBinding.titleBarLayout.centerText.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.centerText.setText("window框架");
        mDataBinding.titleBarLayout.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.leftImg.setOnClickListener(onSingleListener);
        mDataBinding.bottomsheetUtils.setOnClickListener(onSingleListener);
        mDataBinding.dialogUtils.setOnClickListener(onSingleListener);
        mDataBinding.popupwindowUtils.setOnClickListener(onSingleListener);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.debug("back","返回");
    }
}
