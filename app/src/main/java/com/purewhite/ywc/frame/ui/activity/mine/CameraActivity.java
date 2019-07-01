package com.purewhite.ywc.frame.ui.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.config.FileManagerUtils;
import com.purewhite.ywc.frame.config.TagUtils;
import com.purewhite.ywc.frame.databinding.ActivityCameraBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.ConfigUtils;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.config.PhotoUtils;
import com.purewhite.ywc.purewhitelibrary.config.bundle.BundleUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonCallBack;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.config.PictureStype;

import java.io.File;

public class CameraActivity extends MvpActivity<ActivityCameraBinding,PresenterImp> {

    private File picFile;
    private PermissonCallBack permissonCallBack=new PermissonCallBack() {
        @Override
        public void onPermissonSuccess(int requestCode) {
            if (requestCode==1)
            {
                picFile = FileManagerUtils.createTimeFile(CameraActivity.this,FileManagerUtils.FILE_PICTURES);
                PhotoUtils.intentCamera(CameraActivity.this
                        ,"com.purewhite.ywc.frame1.provider"
                        ,picFile, TagUtils.request_camera);
            }
        }

        @Override
        public void onPermissonRepulse(int requestCode, String... permisssons) {

        }
    };

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.left_img:
                    finish();
                    break;
                case R.id.img_obtain:
                    startPermisson(1,permissonCallBack,Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    break;
                case R.id.img_clear:
                    FileManagerUtils.removeFile(CameraActivity.this,FileManagerUtils.FILE_PICTURES);
                    break;
                case R.id.open_img:
                    Bundle build = BundleUtils.buidler()
                            .put(PictureStype.SELECTOR_PIC_MAX_NUM, 9)
                            .put(PictureStype.SKIP_STYPE,PictureStype.SKIP_STYPE_PIC_LIST)
                            .build();
                    skipActivity(PictureActivity.class,build,ConfigUtils.INTENT_REQUEST_PIC);
                    break;
            }
        }
    };

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initView() {
        mDataBinding.actionBar.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.leftImg.setOnClickListener(onSingleListener);
        mDataBinding.actionBar.centerText.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.centerText.setText("图片");
        mDataBinding.imgObtain.setOnClickListener(onSingleListener);
        mDataBinding.imgClear.setOnClickListener(onSingleListener);
        mDataBinding.openImg.setOnClickListener(onSingleListener);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case TagUtils.request_camera:
                if(resultCode==RESULT_OK)
                {
                    String photoPath=null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        photoPath = picFile.getAbsolutePath();
                    }
                    else
                    {
                        if (data.getData()!=null)
                        {
                            photoPath = data.getData().getEncodedPath();
                        }
                    }
                    if (!TextUtils.isEmpty(photoPath))
                    {
                        ImageLoader.newInstance().init(mDataBinding.imageView,photoPath);
                    }
                }
                break;
        }
    }
}
