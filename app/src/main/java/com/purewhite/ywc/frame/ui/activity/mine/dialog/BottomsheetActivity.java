package com.purewhite.ywc.frame.ui.activity.mine.dialog;

import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityBottomsheetBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.window.anim.WindowAnimStyle;
import com.purewhite.ywc.purewhitelibrary.window.dialog.bottomsheet.BottomSheetUtils;
import com.purewhite.ywc.purewhitelibrary.window.utils.WindowPureUtils;

/**
 * @author yuwenchao
 */
public class BottomsheetActivity extends MvpActivity<ActivityBottomsheetBinding,PresenterImp> {


    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.left_img:
                    finish();
                    break;
                case R.id.bottomsheet:
                    createDialog(1);
                    break;
            }
        }
    };



    private BottomSheetUtils dialog_one;
    private void createDialog(int what)
    {
        switch (what)
        {
            case 1:
                if (dialog_one==null)
                {
                    dialog_one=BottomSheetUtils.with(this,R.layout.dialog_one)
                            .addAnim(WindowAnimStyle.bottom_anim);
                }
                dialog_one.show();
                break;
        }
    }

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_bottomsheet;
    }

    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    @Override
    protected void initView() {
        mDataBinding.actionBar.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.leftImg.setOnClickListener(onSingleListener);

        mDataBinding.actionBar.centerText.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.centerText.setText("BottomSheet");

        mDataBinding.bottomsheet.setOnClickListener(onSingleListener);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        WindowPureUtils.onDialogDestory(dialog_one);
    }
}
