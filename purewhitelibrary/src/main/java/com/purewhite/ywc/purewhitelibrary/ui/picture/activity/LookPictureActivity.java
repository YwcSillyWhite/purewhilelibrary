package com.purewhite.ywc.purewhitelibrary.ui.picture.activity;

import android.content.Intent;
import android.view.View;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityLookPictureBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureConfig;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.LookPictureAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;



public class LookPictureActivity extends BaseMvpActivity<PureActivityLookPictureBinding,PresenterImp>{

    private LookPictureAdapter lookPictureAdapter;

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.pure_activity_look_picture;
    }

//    @Override
//    protected void onClickUtils(View view) {
//        switch (view.getId())
//        {
//            case R.id.action_bar_right:
//                break;
//        }
//    }
//
//    @Override
//    public void onClickView(View view) {
//        super.onClickView(view);
//        switch (view.getId())
//        {
//            case R.id.action_bar_layout:
//                break;
//        }
//    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        boolean pictureIsPreview = intent.getBooleanExtra(PictureConfig.pictureIsPreview, false);
        List<ImageBean> imageBeanList = ((List<ImageBean>) intent.getSerializableExtra(PictureConfig.imageBean));
        lookPictureAdapter=new LookPictureAdapter(imageBeanList,pictureIsPreview);
        mDataBinding.viewPager.setAdapter(lookPictureAdapter);
    }

}
