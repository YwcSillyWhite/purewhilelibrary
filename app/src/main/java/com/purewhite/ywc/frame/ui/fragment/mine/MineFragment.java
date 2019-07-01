package com.purewhite.ywc.frame.ui.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.acker.simplezxing.activity.CaptureActivity;
import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.FragMineBinding;
import com.purewhite.ywc.frame.ui.activity.mine.AndroidStudyActivity;
import com.purewhite.ywc.frame.ui.activity.mine.CameraActivity;
import com.purewhite.ywc.frame.ui.activity.mine.CustomMainActivity;
import com.purewhite.ywc.frame.ui.activity.mine.dialog.DialogHomeActiivty;
import com.purewhite.ywc.frame.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.config.bundle.BundleUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.config.PictureStype;

public class MineFragment extends MvpFragment<FragMineBinding,MinePresenter>
        implements MineContract.UiView,View.OnClickListener {


    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    @Override
    public void onClick(View v) {
        if (!ClickUtils.clickable(v))
            return;
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
            case R.id.head_img:
                Bundle build = BundleUtils.buidler()
                        .put(PictureStype.SKIP_STYPE, PictureStype.SKIP_STYPE_PIC_ONLY)
                        .build();
                skipActivity(PictureActivity.class,build);
                break;
            case R.id.zxing:
                startCaptureActivityForResult();
                break;
        }
    }



    private void startCaptureActivityForResult() {
        Intent intent = new Intent(getContext(), CaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP);
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION);
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE);
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF);
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO);
        bundle.putBoolean(CaptureActivity.KEY_SCAN_AREA_FULL_SCREEN, CaptureActivity.VALUE_SCAN_AREA_FULL_SCREEN);
        bundle.putBoolean(CaptureActivity.KEY_NEED_SCAN_HINT_TEXT, CaptureActivity.VALUE_SCAN_HINT_TEXT);
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle);
        startActivityForResult(intent, CaptureActivity.REQ_CODE);
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
        mDataBinding.headImg.setOnClickListener(this);
        mDataBinding.zxing.setOnClickListener(this);
        ImageLoader.newInstance().initCircle(mDataBinding.headImg,R.mipmap.icon_logo);
        mPresenter.showLoad("数据加载中...");

//        mPresenter.obtianShop();
    }






}
