package com.purewhite.ywc.frame1.ui.activity.mine;

import android.text.TextUtils;
import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.config.TagUtils;
import com.purewhite.ywc.frame1.databinding.ActivityWebBinding;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class WebActivity extends MvpActivity<ActivityWebBinding,PresenterImp> {

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {

        }
    };

    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }



    @Override
    protected void initView() {
        String web_uri = getIntent().getStringExtra(TagUtils.web_uri);
        if (TextUtils.isEmpty(web_uri))
            finish();
        mDataBinding.webLayout.getWebView().loadUrl(web_uri);
        mDataBinding.actionBar.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.leftImg.setOnClickListener(onSingleListener);
        mDataBinding.actionBar.centerText.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.centerText.setText("web");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataBinding.webLayout.onDestory();
    }
}
