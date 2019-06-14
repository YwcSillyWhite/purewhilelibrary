package com.purewhite.ywc.frame.ui.activity.mine.dialog;

import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityDialogHomeBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class DialogHomeActiivty extends MvpActivity<ActivityDialogHomeBinding,PresenterImp> {
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.bottomsheetUtils:
                    break;
                case R.id.dialogUtils:
                    skipActivityAnim(DialogActivity.class);
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
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_dialog_home;
    }

    @Override
    protected void initView() {
        mDataBinding.actionBar.centerText.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.centerText.setText("window框架");
        mDataBinding.actionBar.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.leftImg.setOnClickListener(onSingleListener);
        mDataBinding.bottomsheetUtils.setOnClickListener(onSingleListener);
        mDataBinding.dialogUtils.setOnClickListener(onSingleListener);
        mDataBinding.popupwindowUtils.setOnClickListener(onSingleListener);


    }
}
