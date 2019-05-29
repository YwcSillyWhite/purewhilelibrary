package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.ActivityPictureBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.MvpPureActivity;

/**
 * @author yuwenchao
 */
public class PictureActivity extends MvpPureActivity<ActivityPictureBinding,PicturePresenter>
        implements PictureContract.ViewUi {

    private PictureAdapter pictureAdapter;
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
        return R.layout.activity_picture;
    }

    @Override
    protected void initView() {
        pictureAdapter = new PictureAdapter();
        mDataBinding.recyclerView.setAdapter(pictureAdapter);
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this,3));
    }

    @Override
    protected void initRquest() {
        super.initRquest();
        mPresenter.gainImg();
    }

    @Override
    public PictureAdapter getAdapter() {
        return pictureAdapter;
    }
}
