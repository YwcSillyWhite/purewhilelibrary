package com.purewhite.ywc.frame.ui.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.purewhite.ywc.frame.BuildConfig;
import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.config.FileManagerUtils;
import com.purewhite.ywc.frame.config.TagUtils;
import com.purewhite.ywc.frame.databinding.ActivityCameraBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.PhotoUtils;
import com.purewhite.ywc.purewhitelibrary.config.permisson.PermissonCallBack;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureUtils;

import java.io.File;

public class CameraActivity extends MvpActivity<ActivityCameraBinding,PresenterImp>{

    private File picFile;
    private PermissonCallBack permissonCallBack=new PermissonCallBack() {
        @Override
        public void onPermissonSuccess(int requestCode) {
            switch (requestCode)
            {
                case 1:
                    picFile = FileManagerUtils.createTimeFile(CameraActivity.this,FileManagerUtils.FILE_PICTURES);
                    PhotoUtils.intentCamera(CameraActivity.this
                            , BuildConfig.APPLICATION_ID+".fileprovider"
                            ,picFile, TagUtils.request_camera);
                    break;
                case 2:
                    PictureUtils.buidler()
                            .setImageMax(6)
                            .setLineNum(3)
                            .build(CameraActivity.this);
                    break;
            }
        }

        @Override
        public void onPermissonRepulse(int requestCode, String... permisssons) {

        }
    };


    @Override
    protected void onClickUtils(View view) {
        switch (view.getId())
        {
            case R.id.left_img:
                backActivity();
                break;
            case R.id.img_obtain:
                startPermisson(1,permissonCallBack,Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.img_clear:
                FileManagerUtils.removeFile(CameraActivity.this,FileManagerUtils.FILE_PICTURES);
                break;
            case R.id.open_img:
                startPermisson(2,permissonCallBack,Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
        }

    }




    @Override
    protected int getLayout() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initView() {
        mDataBinding.titleBarLayout.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.centerText.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.centerText.setText("图片");
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
