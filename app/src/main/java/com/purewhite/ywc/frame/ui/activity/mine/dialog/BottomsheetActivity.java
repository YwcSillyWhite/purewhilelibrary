package com.purewhite.ywc.frame.ui.activity.mine.dialog;

import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityBottomsheetBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhitelibrary.window.anim.WindowAnimStyle;
import com.purewhite.ywc.purewhitelibrary.window.dialog.utils.BaseDialogUtils;
import com.purewhite.ywc.purewhitelibrary.window.dialog.utils.DialogUtils;
import com.purewhite.ywc.purewhitelibrary.window.utils.WindowPureUtils;

/**
 * @author yuwenchao
 */
public class BottomsheetActivity extends MvpActivity<ActivityBottomsheetBinding, BasePresenter> {


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



    private BaseDialogUtils dialog_one;
    private void createDialog(int what)
    {
        switch (what)
        {
            case 1:
                if (dialog_one==null)
                {
                    dialog_one= DialogUtils.builder()
                            .setContentView(R.layout.dialog_one)
                            .setAnim(WindowAnimStyle.bottom_anim_window)
                            .buildBottom(this);
                }
                dialog_one.show();
                break;
        }
    }



    @Override
    protected int getLayout() {
        return R.layout.activity_bottomsheet;
    }


    @Override
    protected void initView() {
        mDataBinding.titleBarLayout.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.leftImg.setOnClickListener(onSingleListener);

        mDataBinding.titleBarLayout.centerText.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.centerText.setText("BottomSheet");

        mDataBinding.bottomsheet.setOnClickListener(onSingleListener);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        WindowPureUtils.onDialogDestory(dialog_one);
    }
}
