package com.purewhite.ywc.frame.ui.fragment.mine;

import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.FragMineBinding;
import com.purewhite.ywc.frame.ui.activity.mine.AndroidStudyActivity;
import com.purewhite.ywc.frame.ui.activity.mine.CameraActivity;
import com.purewhite.ywc.frame.ui.activity.mine.CustomMainActivity;
import com.purewhite.ywc.frame.ui.activity.mine.dialog.DialogHomeActiivty;
import com.purewhite.ywc.frame.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

public class MineFragment extends MvpFragment<FragMineBinding,MinePresenter> implements MineContract.UiView {


    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }


    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
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
    };



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
        mDataBinding.androidStudy.setOnClickListener(onSingleListener);
        mDataBinding.bottomNavigation.setOnClickListener(onSingleListener);
        mDataBinding.dialog.setOnClickListener(onSingleListener);
        mDataBinding.camera.setOnClickListener(onSingleListener);
        ImageLoader.newInstance().initCircle(mDataBinding.headImg,R.mipmap.icon_logo);
    }


}
