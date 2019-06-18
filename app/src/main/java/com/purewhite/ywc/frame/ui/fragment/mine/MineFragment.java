package com.purewhite.ywc.frame.ui.fragment.mine;

import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.FragMineBinding;
import com.purewhite.ywc.frame.ui.activity.mine.AndroidStudyActivity;
import com.purewhite.ywc.frame.ui.activity.mine.CameraActivity;
import com.purewhite.ywc.frame.ui.activity.mine.CustomMainActivity;
import com.purewhite.ywc.frame.ui.activity.mine.dialog.DialogHomeActiivty;
import com.purewhite.ywc.frame.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

public class MineFragment extends MvpFragment<FragMineBinding,MinePresenter>
        implements MineContract.UiView,View.OnClickListener {


    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    @Override
    public void onClick(View v) {
        if (ClickUtils.clickable(v))
        {
            switch (v.getId())
            {
                case R.id.android_study:
                    skipActivity(AndroidStudyActivity.class);
                    break;
                case R.id.bottom_navigation:
                    skipActivity(CustomMainActivity.class);
                    break;
                case R.id.dialog:
                    skipActivity(DialogHomeActiivty.class);
                    break;
                case R.id.camera:
                    skipActivity(CameraActivity.class);
                    break;
            }
        }
    }




    @Override
    protected MinePresenter creartPresenter() {
        return new MinePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_mine;
    }

    @Override
    protected void initView() {
        mDataBinding.actionBar.centerText.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.centerText.setText("个人中心");
        mDataBinding.androidStudy.setOnClickListener(this);
        mDataBinding.bottomNavigation.setOnClickListener(this);
        mDataBinding.dialog.setOnClickListener(this);
        mDataBinding.camera.setOnClickListener(this);
        ImageLoader.newInstance().initCircle(mDataBinding.headImg,R.mipmap.icon_logo);
    }



}
