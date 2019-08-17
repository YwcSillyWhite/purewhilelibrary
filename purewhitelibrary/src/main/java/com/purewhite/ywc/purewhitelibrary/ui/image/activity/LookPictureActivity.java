package com.purewhite.ywc.purewhitelibrary.ui.image.activity;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityLookPictureBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class LookPictureActivity extends BaseMvpActivity<PureActivityLookPictureBinding,PresenterImp> {
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.pure_activity_look_picture;
    }

    @Override
    protected void initView() {
//        mDataBinding.viewPager.setAdapter();
    }
}
