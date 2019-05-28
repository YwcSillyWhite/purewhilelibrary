package com.purewhite.ywc.frame1.ui.activity.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.ActivityDialogBinding;
import com.purewhite.ywc.frame1.ui.adapter.DialogAdapter;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.view.dialog.DialogUtils;

import java.util.Arrays;

public class DialogActivity extends MvpActivity<ActivityDialogBinding,PresenterImp> {

    private DialogUtils dialog,dialogList;
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
                case R.id.dialog_two:
                    if (dialogList==null)
                    {
                        dialogList=dialogList();
                    }
                    dialogList.show();
                    break;
                case R.id.dialog_sure:
                    dialog.dismiss();
                    break;
                case R.id.dialog_clear:
                    dialog.dismiss();
                    break;
                case R.id.list_clear:
                    dialogList.dismiss();
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
                .setScreenWidth(0.8f)
                .setAnim(DialogUtils.DialogStyle.left_anim)
                .setCancelable(false);
    }


    private DialogUtils dialogList()
    {
        DialogAdapter dialogAdapter = new DialogAdapter(Arrays.asList(getResources().getStringArray(R.array.dialog_list)));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        return new DialogUtils(this).setDialogView(R.layout.dialog_two)
                .setOnClick(onSingleListener)
                .setChildText(R.id.list_clear,true)
                .setChildRecycler(R.id.recycler_view,dialogAdapter,linearLayoutManager)
                .setScreenWidth(1f)
                .setAnim(DialogUtils.DialogStyle.bottom_anim)
                .setGravity(Gravity.BOTTOM)
                .setAllFinish(this);
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
        mDataBinding.dialogTwo.setOnClickListener(onSingleListener);
        mDataBinding.dialog.setOnClickListener(onSingleListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null)
            dialog.onDestroy();
        if (dialogList!=null)
            dialogList.onDestroy();
    }
}
