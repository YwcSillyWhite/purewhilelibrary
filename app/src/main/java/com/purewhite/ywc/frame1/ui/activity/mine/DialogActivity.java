package com.purewhite.ywc.frame1.ui.activity.mine;

import android.app.Dialog;
import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.ActivityDialogBinding;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.view.dialog.DialogUtils;

public class DialogActivity extends MvpActivity<ActivityDialogBinding,PresenterImp> {

    private DialogUtils dialog;
    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.dialog:
                    if (dialog==null)
                    {
                        dialog = dialog();
                    }
                    dialog.show();
                    break;
                case R.id.dialog_sure:
                    dialog.dismiss();
                    break;
                case R.id.dialog_clear:
                    dialog.dismiss();
                    break;
            }
        }
    };

    private DialogUtils dialog()
    {
        return new DialogUtils(this).setDialogView(R.layout.dialog_one)
                .setOnClick(onSingleListener)
                .setChildText(R.id.dialog_content,"纯白框架必然是精品",false)
                .setChildText(R.id.dialog_sure,true)
                .setChildText(R.id.dialog_clear,true)
                .setScreenWidth(0.8f);

    }


    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void initView() {
        mDataBinding.actionBar.centerText.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.centerText.setText("dialog");
        mDataBinding.dialog.setOnClickListener(onSingleListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.onDestroy();
    }
}
