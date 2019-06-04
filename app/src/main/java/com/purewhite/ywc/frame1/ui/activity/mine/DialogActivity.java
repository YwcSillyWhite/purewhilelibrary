package com.purewhite.ywc.frame1.ui.activity.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.ActivityDialogBinding;
import com.purewhite.ywc.frame1.ui.adapter.DialogAdapter;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.view.dialog.bottomsheet.BottomSheetUtils;
import com.purewhite.ywc.purewhitelibrary.view.dialog.dialog.DialogStyle;

import java.util.Arrays;

public class DialogActivity extends MvpActivity<ActivityDialogBinding,PresenterImp> {

    private BottomSheetUtils dialog,bottomSheetUtils;
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
                    if (bottomSheetUtils==null)
                    {
                        bottomSheetUtils=dialogList();
                    }
                    bottomSheetUtils.show();
                    break;
                case R.id.dialog_sure:
                    dialog.dismiss();
                    break;
                case R.id.dialog_clear:
                    dialog.dismiss();
                    break;
                case R.id.list_clear:
                    bottomSheetUtils.dismiss();
                    break;
            }
        }
    };

    private BottomSheetUtils dialog()
    {
        return BottomSheetUtils.with(this)
                .addLayout(R.layout.dialog_one)
                .setOnClickListener(onSingleListener)
                .setTextView(R.id.dialog_content,"纯白框架必然是精品",false)
                .setTextView(R.id.dialog_sure,"确定",true)
                .setTextView(R.id.dialog_clear,"取消",true)
                .addAnim(DialogStyle.left_anim);
    }


    private BottomSheetUtils dialogList()
    {
        DialogAdapter dialogAdapter = new DialogAdapter(Arrays.asList(getResources().getStringArray(R.array.dialog_list)));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        return BottomSheetUtils.with(this)
                .addLayout(R.layout.dialog_two)
                .setOnClickListener(onSingleListener)
                .setTextView(R.id.list_clear,"确定",true)
                .setRecycler(R.id.recycler_view,dialogAdapter,linearLayoutManager)
                .addAnim(DialogStyle.bottom_anim);
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
        if (bottomSheetUtils!=null)
            bottomSheetUtils.onDestroy();
    }
}
