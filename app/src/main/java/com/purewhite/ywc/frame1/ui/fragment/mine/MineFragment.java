package com.purewhite.ywc.frame1.ui.fragment.mine;

import android.Manifest;
import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.FragMineBinding;
import com.purewhite.ywc.frame1.ui.activity.mine.AndroidStudyActivity;
import com.purewhite.ywc.frame1.ui.activity.mine.CameraActivity;
import com.purewhite.ywc.frame1.ui.activity.mine.CustomMainActivity;
import com.purewhite.ywc.frame1.ui.activity.mine.DialogActivity;
import com.purewhite.ywc.frame1.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.app.activity.ActivitySkipUtils;
import com.purewhite.ywc.purewhitelibrary.config.ToastUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonCallBack;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

public class MineFragment extends MvpFragment<FragMineBinding,MinePresenter> implements MineContract.UiView {


    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    private PermissonCallBack permissonCallBack=new PermissonCallBack() {
        @Override
        public void onPermissonSuccess(int requestCode) {
            ActivitySkipUtils.startActivityAnim(CameraActivity.class);
        }

        @Override
        public void onPermissonRepulse(int requestCode, String... permisssons) {
            ToastUtils.show(permisssons.toString());
        }
    };

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.android_study:
                    ActivitySkipUtils.startActivityAnim(AndroidStudyActivity.class);
                    break;
                case R.id.bottom_navigation:
                    ActivitySkipUtils.startActivityAnim(CustomMainActivity.class);
                    break;
                case R.id.dialog:
                    ActivitySkipUtils.startActivityAnim(DialogActivity.class);
                    break;
                case R.id.camera:
                    startPermisson(permissonCallBack,Manifest.permission.CAMERA
                            ,Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
