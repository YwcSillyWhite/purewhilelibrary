package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.config.bundle.BundleUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityPictureBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.PictureAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.PictureWindowAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.config.PictureStype;
import com.purewhite.ywc.purewhitelibrary.ui.picture.manager.PicSeletorManager;
import com.purewhite.ywc.purewhitelibrary.view.recyclerview.AroundItemDecoration;
import com.purewhite.ywc.purewhitelibrary.window.anim.WindowAnimStyle;
import com.purewhite.ywc.purewhitelibrary.window.dialog.utils.DialogUtils;
import com.purewhite.ywc.purewhitelibrary.window.utils.WindowPureUtils;

import java.util.List;

/**
 * @author yuwenchao
 */
public class PictureActivity extends BaseMvpActivity<PureActivityPictureBinding,PicturePresenter>
        implements PictureContract.ViewUi,View.OnClickListener, OnItemListener {

    private PictureAdapter pictureAdapter;

    private PictureWindowAdapter pictureWindowAdapter;

    //选择最大图片
    private int stype_pic_num;



    //recycler点击
    @Override
    public void OnClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView) {
        if (adapter instanceof PictureWindowAdapter)
        {
            dialogUtils.dismiss();
            PictureWindowAdapter pictureWindowAdapter = (PictureWindowAdapter) adapter;
            int seleterPosition = pictureWindowAdapter.getSeleterPosition();
            if (seleterPosition!=position)
            {
                Folder folder = pictureWindowAdapter.obtainT(position);
                pictureWindowAdapter.setSeleterPosition(position);
                mDataBinding.recyclerView.scrollToPosition(0);
                pictureAdapter.flush(folder.getImageBeanList());
                mDataBinding.pictureTextTag.setText(folder.getName());
            }
        }
        else if (adapter instanceof PictureAdapter)
        {
            if (itemView)
            {
                PictureAdapter pictureAdapter = (PictureAdapter) adapter;
                final int skip_stype = pictureAdapter.getSkip_stype();
                if (skip_stype==PictureStype.SKIP_STYPE_PIC_ONLY)
                {

                }
                else
                {

                }
            }
            else
            {
                final int id = view.getId();
                if (id==R.id.pic_click)
                {
                    if (PicSeletorManager.newInstance().obtainPicCount()>0)
                    {
                        mDataBinding.textViewButton.setEnabled(true);
                        mDataBinding.textViewButton.setText(PicSeletorManager.newInstance().obtainPicContent()+"完成");
                    }
                    else
                    {
                        mDataBinding.textViewButton.setEnabled(false);
                        mDataBinding.textViewButton.setText("完成");
                    }
                }
            }
        }
    }



    @Override
    public void onClick(View v) {
        if (ClickUtils.clickable(v))
        {
            final int id = v.getId();
            if(id==R.id.action_bar_left_img)
            {
                backActivity();
            }
            else if(id==R.id.window_layout)
            {
                showWindow(1);
            }
            else if (id==R.id.text_view_button)
            {
                if (PicSeletorManager.newInstance().obtainPicCount()>0)
                {
                    Bundle build = BundleUtils.buidler()
                            .put(PictureStype.REQUEST_PIC, PicSeletorManager.newInstance().getListPath())
                            .build();
                    backActivity(build,PictureStype.STYPE_PIC_OK);
                }
            }
            else if (id==R.id.text_view_look)
            {

            }
        }

    }


    @Override
    protected void beforeView() {
        super.beforeView();
        BarUtils.obtianBarConfig().with(this)
                .setStatusBarHideFlag()
                .setStatusBarTextColorFlag(true)
                .build();
    }

    @Override
    protected void afterView() {
        super.afterView();
        BarUtils.obtianTitleConfig().setTitleBarPadding(mDataBinding.actionLayout);
    }

    @Override
    protected PicturePresenter creartPresenter() {
        return new PicturePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.pure_activity_picture;
    }




    @Override
    protected void initView() {
        Intent intent = getIntent();
        int skip_stype = intent.getIntExtra(PictureStype.SKIP_STYPE, PictureStype.SKIP_STYPE_PIC_ONLY);
        int selector = intent.getIntExtra(PictureStype.SELECTOR_PIC_MAX_NUM, 0);
        if (skip_stype==PictureStype.SKIP_STYPE_PIC_ONLY)
        {
            PicSeletorManager.newInstance().setPicSize(0);
            mDataBinding.textViewButton.setVisibility(View.GONE);
            mDataBinding.textViewLook.setVisibility(View.GONE);
        }
        else
        {
            PicSeletorManager.newInstance().setPicSize(selector);
            mDataBinding.textViewButton.setVisibility(View.VISIBLE);
            mDataBinding.textViewLook.setVisibility(View.VISIBLE);
        }

        pictureAdapter = new PictureAdapter(skip_stype);
        pictureAdapter.setOnItemListener(this);
        mDataBinding.recyclerView.setAdapter(pictureAdapter);
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mDataBinding.recyclerView.addItemDecoration(new AroundItemDecoration(getResources().getDimensionPixelOffset(R.dimen.dp_1)));

        mDataBinding.windowLayout.setOnClickListener(this);
        mDataBinding.actionBarLeftImg.setOnClickListener(this);
        mDataBinding.textViewButton.setOnClickListener(this);
        mDataBinding.textViewLook.setOnClickListener(this);
    }


    @Override
    protected void initRquest() {
        super.initRquest();
        mPresenter.gainImg();
    }

    @Override
    public void obtianListFolder(List<Folder> folders) {
        if (folders!=null&&folders.size()>0)
        {
            pictureWindowAdapter=new PictureWindowAdapter();
            dialogUtils=DialogUtils.withNo(this,R.layout.pure_window_picture)
                    .setGravity(Gravity.BOTTOM)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            mDataBinding.relativeLayout.setVisibility(View.GONE);
                        }
                    })
                    .setSplace(1f,0,0,mDataBinding.bottomLinearLayout.getHeight())
                    .setRecyclerView(R.id.recycler_view,pictureWindowAdapter,new LinearLayoutManager(this))
                    .addAnim(WindowAnimStyle.bottom_anim_window);
            pictureWindowAdapter.flush(folders);
            pictureWindowAdapter.setSeleterPosition(0);
            pictureAdapter.flush(folders.get(0).getImageBeanList());
            pictureWindowAdapter.setOnItemListener(this);
        }
    }


    private DialogUtils dialogUtils;
    private void showWindow(int tag)
    {
        switch (tag)
        {
            case 1:
                if (dialogUtils!=null)
                {

                    dialogUtils.show(mDataBinding.relativeLayout);
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        PicSeletorManager.newInstance().onDestory();
        WindowPureUtils.onDialogDestory(dialogUtils);
    }
}
