package com.purewhite.ywc.frame.ui.activity.mine;

import android.graphics.Bitmap;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.config.TagUtils;
import com.purewhite.ywc.frame.databinding.ActivityWebBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class WebActivity extends MvpActivity<ActivityWebBinding,PresenterImp> {

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.left_img:
//                    if (!mDataBinding.webLayout.isRollback())
//                    {
//                        finish();
//                    }
                    break;
            }
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

        mDataBinding.actionBar.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.leftImg.setOnClickListener(onSingleListener);
        mDataBinding.actionBar.centerText.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.centerText.setText("web");

//        mDataBinding.webView.loadUrl(web_uri);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    private WebViewClient webViewClient=new WebViewClient()
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mDataBinding.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mDataBinding.progressBar.setVisibility(View.GONE);
        }
    };


    private WebChromeClient webChromeClient=new WebChromeClient()
    {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mDataBinding.progressBar.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            mDataBinding.actionBar.centerText.setText(TextUtils.isEmpty(title)?"web":title);
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
        {
//            if (mDataBinding.webLayout.isRollback())
//            {
//                return true;
//            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
