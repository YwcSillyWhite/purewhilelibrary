package com.purewhite.ywc.purewhitelibrary.ui.picture.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.config.bundle.BundleUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityPictureSelectBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureConfig;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureManager;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.PictureSelectAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.contract.PictureSelectContract;
import com.purewhite.ywc.purewhitelibrary.ui.picture.presenter.PictureSelectPresenter;

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

    @Override
    protected void onClickUtils(View view) {
        final int id = view.getId();
        if (id == R.id.action_bar_left) {
            finish();
        }
        else if (id==R.id.action_bar_center)
        {

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
    }


    @Override
    protected void initRquest() {
        super.initRquest();
        mPresenter.requestList();
    }


    @Override
    public void responList(List<Folder> folderList) {
        pictureSelectAdapter.flush(folderList,0);
    }
}
