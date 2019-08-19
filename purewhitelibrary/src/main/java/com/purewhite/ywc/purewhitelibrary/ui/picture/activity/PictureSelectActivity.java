package com.purewhite.ywc.purewhitelibrary.ui.picture.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.config.bundle.BundleUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityPictureSelectBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureConfig;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureManager;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.PictureSelectAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.PictureWindowAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.contract.PictureSelectContract;
import com.purewhite.ywc.purewhitelibrary.ui.picture.presenter.PictureSelectPresenter;
import com.purewhite.ywc.purewhitelibrary.window.anim.WindowAnimStyle;
import com.purewhite.ywc.purewhitelibrary.window.base.WindowViewUtils;
import com.purewhite.ywc.purewhitelibrary.window.popup.PopupWindowUtils;

import java.util.ArrayList;
import java.util.List;

public class PictureSelectActivity extends BaseMvpActivity<PureActivityPictureSelectBinding, PictureSelectPresenter>
        implements PictureSelectContract.UiView {

    private PictureSelectAdapter pictureSelectAdapter;

    private OnItemListener onItemListener=new OnItemListener() {
        @Override
        public void onClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView) {
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
                            .put(PictureConfig.pictureIsPreview, false)
                            .put(PictureConfig.picturePagerPosition, positionReal)
                            .build();
                    skipActivity(LookPictureActivity.class,1,build);
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
                popupWindowUtils.showAsDropDown(mDataBinding.barLayoutRelative);
            }
        }
    }

    @Override
    protected PictureSelectPresenter creartPresenter() {
        return new PictureSelectPresenter();
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
        popupWindowUtils = PopupWindowUtils.builder().setContentView(R.layout.pure_window_picture)
                .setAnim(WindowAnimStyle.top_anim_window)
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
    }

}
