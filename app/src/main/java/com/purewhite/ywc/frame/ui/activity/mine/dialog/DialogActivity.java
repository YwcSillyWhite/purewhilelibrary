package com.purewhite.ywc.frame.ui.activity.mine.dialog;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityDialogBinding;
import com.purewhite.ywc.frame.ui.adapter.DialogAdapter;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.window.anim.WindowAnimStyle;
import com.purewhite.ywc.purewhitelibrary.window.dialog.utils.DialogUtils;
import com.purewhite.ywc.purewhitelibrary.window.utils.WindowPureUtils;

import java.util.Arrays;

public class DialogActivity extends MvpActivity<ActivityDialogBinding,PresenterImp> implements View.OnClickListener{

    private DialogUtils dialogOne,dialogTwo;

    @Override
    public void onClick(View v) {
        if (!ClickUtils.clickable(v))
            return;
        switch (v.getId())
        {
            case R.id.dialog:
                createDialog(1);
                break;
            case R.id.dialog_two:
                createDialog(2);
                break;
            case R.id.dialog_three:
                mPresenter.showLoad("加载数据中...");
                break;
            case R.id.dialog_sure:
                dialogOne.dismiss();
                break;
            case R.id.dialog_clear:
                dialogOne.dismiss();
                break;
            case R.id.list_clear:
                dialogTwo.dismiss();
                break;
            case R.id.left_img:
                finish();
                break;
        }
    }


    private void createDialog(int dialogType)
    {
        switch (dialogType)
        {
            case 1:
                if (dialogOne==null)
                {
                    dialogOne=DialogUtils.with(this,R.layout.dialog_one)
                            .setOnClickListener(this)
                            .setTextView(R.id.dialog_content,"纯白框架必然是精品",false)
                            .setTextView(R.id.dialog_sure,"确定",true)
                            .setTextView(R.id.dialog_clear,"取消",true)
                            .addAnim(WindowAnimStyle.left_anim_window)
                            .setScreenWidth(0.8f);
                }
                dialogOne.show();
                break;
            case 2:
                if (dialogTwo==null)
                {
                    DialogAdapter dialogAdapter = new DialogAdapter(Arrays.asList(getResources().getStringArray(R.array.dialog_list)));
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    dialogTwo=DialogUtils.with(this,R.layout.dialog_two)
                            .setOnClickListener(this)
                            .setTextView(R.id.list_clear,"确定",true)
                            .setRecyclerView(R.id.recycler_view,dialogAdapter,linearLayoutManager)
                            .addAnim(WindowAnimStyle.bottom_anim_window)
                            .setGravity(Gravity.BOTTOM)
                            .setScreenWidth(1f);
                }
                dialogTwo.show();
                break;
                }

    }

    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    @Override
    protected PresenterImp creartPresenter() {
        return new PresenterImp();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void initView() {
        mDataBinding.actionBar.centerText.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.centerText.setText("dialog");
        mDataBinding.actionBar.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.leftImg.setOnClickListener(this);
        mDataBinding.dialogTwo.setOnClickListener(this);
        mDataBinding.dialog.setOnClickListener(this);
        mDataBinding.dialogThree.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WindowPureUtils.onDialogDestory(dialogOne,dialogTwo);
    }



}
