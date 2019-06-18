package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
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

import java.util.List;

/**
 * @author yuwenchao
 */
public class PictureActivity extends BaseMvpActivity<PureActivityPictureBinding,PicturePresenter>
        implements PictureContract.ViewUi,View.OnClickListener, OnItemListener {

    private PictureAdapter pictureAdapter;

    private PictureWindowAdapter pictureWindowAdapter;
    //获取类型
    private int style_request;
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
                pictureAdapter.flush(folder.getImageBeanList());
                mDataBinding.pictureTextTag.setText(folder.getName());
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
        style_request = intent.getIntExtra(PictureStype.STYPE_REQUEST, PictureStype.STYPE_PIC_REQUEST);
        if (style_request==PictureStype.STYPE_PIC_REQUEST)
        {
            stype_pic_num = intent.getIntExtra(PictureStype.STYPE_PIC_NUM, 0);
            if (stype_pic_num>0)
            {
                PicSeletorManager.newInstance().setPicSize(stype_pic_num);
            }
        }
        pictureAdapter = new PictureAdapter();
        mDataBinding.recyclerView.setAdapter(pictureAdapter);
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mDataBinding.recyclerView.addItemDecoration(new AroundItemDecoration(getResources().getDimensionPixelOffset(R.dimen.dp_1)));
        mDataBinding.windowLayout.setOnClickListener(this);
        mDataBinding.actionBarLeftImg.setOnClickListener(this);
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


}
