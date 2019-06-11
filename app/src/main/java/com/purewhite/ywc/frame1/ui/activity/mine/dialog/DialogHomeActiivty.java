package com.purewhite.ywc.frame1.ui.activity.mine.dialog;

import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.ActivityDialogHomeBinding;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class DialogHomeActiivty extends MvpActivity<ActivityDialogHomeBinding,PresenterImp>  {
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
        mDataBinding.bottomsheetUtils.setOnClickListener(onSingleListener);
        mDataBinding.dialogUtils.setOnClickListener(onSingleListener);
        mDataBinding.popupwindowUtils.setOnClickListener(onSingleListener);
    }
}
