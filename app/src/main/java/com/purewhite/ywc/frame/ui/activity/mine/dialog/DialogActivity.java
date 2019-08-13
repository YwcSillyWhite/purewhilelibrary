package com.purewhite.ywc.frame.ui.activity.mine.dialog;


import android.view.Gravity;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityDialogBinding;
import com.purewhite.ywc.frame.ui.adapter.DialogAdapter;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.window.anim.WindowAnimStyle;
import com.purewhite.ywc.purewhitelibrary.window.dialog.BaseDialogBuilder;
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
                    dialogOne= new BaseDialogBuilder()
                            .setContentView(R.layout.dialog_one)
                            .setOnClickListener(this)
                            .setAnim(WindowAnimStyle.left_anim_window)
                            .buildDialog(this)
                            .setTextView(R.id.dialog_content,"纯白框架必然是精品",false)
                            .setTextView(R.id.dialog_sure,"确定",true)
                            .setTextView(R.id.dialog_clear,"取消",true)
                            .setSplace(0.8f,0);
                }
                dialogOne.show();
                break;
            case 2:
                if (dialogTwo==null)
                {
                    DialogAdapter dialogAdapter = new DialogAdapter(Arrays.asList(getResources().getStringArray(R.array.dialog_list)));
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    dialogTwo= new BaseDialogBuilder()
                            .setContentView(R.layout.dialog_two)
                            .setOnClickListener(this)
                            .setAnim(WindowAnimStyle.bottom_anim_window)
                            .buildDialog(this)
                            .setTextView(R.id.list_clear,"确定",true)
                            .setRecyclerView(R.id.recycler_view,dialogAdapter,linearLayoutManager)
                            .setSplace(1f,0,Gravity.BOTTOM);
                }
                dialogTwo.show();
                break;
                }

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
        mDataBinding.titleBarLayout.centerText.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.centerText.setText("dialog");
        mDataBinding.titleBarLayout.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.leftImg.setOnClickListener(this);
        mDataBinding.dialogTwo.setOnClickListener(this);
        mDataBinding.dialog.setOnClickListener(this);
        mDataBinding.dialogThree.setOnClickListener(this);
        mDataBinding.tabLayout.addTab(mDataBinding.tabLayout.newTab().setText("我的"));
        mDataBinding.tabLayout.addTab(mDataBinding.tabLayout.newTab().setText("你的"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WindowPureUtils.onDialogDestory(dialogOne,dialogTwo);
    }



}
