package com.example.tbslibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.tbslibrary.app.TbsAppUtils;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * 防止内存泄露
 */
public class X5WebView extends RelativeLayout {

    private WebView webView;
    private ProgressBar progressBar;

    public WebView getWebView() {
        return webView;
    }

    public X5WebView(Context context) {
        this(context,null);
    }

    public X5WebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public X5WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        webView = new WebView(TbsAppUtils.getApplication());
        addView(webView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        initSetting(webView.getSettings());

        progressBar = new ProgressBar(getContext());

//        progressBar.setProgressDrawable();

    }

    private void initSetting(WebSettings webSetting) {
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }



    //是否能回退
    public boolean isRollback()
    {
        if (webView!=null)
        {
            //是否能回退
            if (webView.canGoBack())
            {
                webView.goBack();
                return true;
            }
        }
        return false;
    }


    //释放资源
    public void onDestory() {
        if( webView!=null) {
            webView.setVisibility(View.GONE);
            webView.removeAllViews();
            webView.destroy();
        }
    }




}
