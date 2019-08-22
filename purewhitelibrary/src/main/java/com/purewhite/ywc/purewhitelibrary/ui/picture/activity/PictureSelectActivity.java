package com.purewhite.ywc.purewhitelibrary.ui.picture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.config.bundle.BundleUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityPictureSelectBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureConfig;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureManager;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.PictureSelectAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.PictureWindowAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.picture.contract.PictureSelectContract;
import com.purewhite.ywc.purewhitelibrary.ui.picture.presenter.PictureSelectPresenter;
import com.purewhite.ywc.purewhitelibrary.window.anim.WindowAnimStyle;
import com.purewhite.ywc.purewhitelibrary.window.popup.PopupWindowUtils;
import com.purewhite.ywc.purewhitelibrary.window.utils.WindowPureUtils;

import java.util.ArrayList;
import java.util.List;

public class PictureSelectActivity extends BaseMvpActivity<PureActivityPictureSelectBinding, PictureSelectPresenter>
        implements PictureSelectContract.UiView {

    private PictureSelectAdapter pictureSelectAdapter;

    private OnItemListener onItemListener=new OnItemListener() {
        @Override
        public void onClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView) {
            if (itemView)
            {
                if (adapter instanceof PictureSelectAdapter)
                {
                    int positionReal = ((PictureSelectAdapter) adapter).obtainPosition(position);
                    //拍照
                    if (positionReal<0)
                    {

                    }
                    else
                    {
                        Bundle build = BundleUtils.buidler().put(PictureConfig.imageBean, ((PictureSelectAdapter) adapter).obtainData())
                                .put(PictureConfig.picturePagerPosition, positionReal)
                                .build();
                        skipActivity(LookPictureActivity.class,PictureConfig.intent_picture_to_look,build);
                    }
                }
                else if (adapter instanceof PictureWindowAdapter)
                {
                    WindowPureUtils.onPopupDestory(popupWindowUtils);
                    if(((PictureWindowAdapter) adapter).flushSelectPosition(position))
                    {
                        mDataBinding.actionBarCenter.setText(pictureSelectAdapter.flush(((PictureWindowAdapter) adapter).obtainData(),position));
                    }
                }
            }
            else
            {
                if (adapter instanceof PictureSelectAdapter)
                {
                    PictureSelectAdapter pictureSelectAdapter = (PictureSelectAdapter) adapter;
                    final int id = view.getId();
                    if (id==R.id.pic_click)
                    {
                        ImageBean imageBean = pictureSelectAdapter.obtainT(pictureSelectAdapter.obtainPosition(position));
                        if ( PictureManager.newInstance().alterImage(imageBean.getPath()))
                        {
                            pictureSelectAdapter.flushPosition(position);
                            setViewStatue();
                        }
                    }
                }
            }

        }
    };

    private PictureWindowAdapter pictureWindowAdapter;
    private PopupWindowUtils popupWindowUtils;

    @Override
    protected void onClickUtils(View view) {
        final int id = view.getId();
        if (id == R.id.action_bar_left) {
            finish();
        }
        else if (id==R.id.action_bar_center)
        {
            if (pictureWindowAdapter.getItemCount()>0)
            {
                mDataBinding.pureLucencyLayout.getRoot().setVisibility(View.VISIBLE);
                popupWindowUtils.showAsDropDown(mDataBinding.pureViewLine);
            }
        }
        else if (id==R.id.picture_sure)
        {

        }
        else if (id==R.id.picture_preview)
        {
            skipActivity(LookPictureActivity.class,PictureConfig.intent_picture_to_look);
        }
    }

    //设置完成和预览的状态
    private void setViewStatue()
    {
        List<String> selectorList = PictureManager.newInstance().getSelectorList();
        if (selectorList.size()>0)
        {
            mDataBinding.pictureSure.setEnabled(true);
            mDataBinding.picturePreview.setEnabled(true);
            if (selectorList.size()>=PictureManager.newInstance().getImageMax())
            {
                mDataBinding.pictureSure.setText("已完成");
            }
            else
            {
                mDataBinding.pictureSure.setText("已完成 "+selectorList.size()+"/"+PictureManager.newInstance().getImageMax());
            }
        }
        else
        {
            mDataBinding.pictureSure.setEnabled(false);
            mDataBinding.picturePreview.setEnabled(false);
            mDataBinding.pictureSure.setText("请选择");
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.pure_activity_picture_select;
    }


    @Override
    protected void initView() {
        pictureSelectAdapter = new PictureSelectAdapter();
        pictureSelectAdapter.setOnItemListener(onItemListener);
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, PictureManager.newInstance().getLineNum()));
        mDataBinding.recyclerView.setAdapter(pictureSelectAdapter);
        pictureWindowAdapter = new PictureWindowAdapter(new ArrayList<>());
        pictureWindowAdapter.setOnItemListener(onItemListener);
        popupWindowUtils = PopupWindowUtils.builder().setContentView(R.layout.pure_window_picture)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mDataBinding.pureLucencyLayout.getRoot().setVisibility(View.GONE);
                    }
                })
                .Builder(this)
                .setRecyclerView(R.id.recycler_view, pictureWindowAdapter, new LinearLayoutManager(this));
    }


    @Override
    protected void initRquest() {
        super.initRquest();
        mPresenter.requestList();
    }


    @Override
    public void responList(List<Folder> folderList) {
        pictureSelectAdapter.flush(folderList,0);
        pictureWindowAdapter.flush(folderList);
        setViewStatue();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case PictureConfig.intent_picture_to_look:
                if (resultCode==PictureConfig.back_look_to_picture)
                {

                }
                else
                {
                    pictureSelectAdapter.notifyDataSetChanged();
                    setViewStatue();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WindowPureUtils.onPopupDestory(popupWindowUtils);
    }
}
