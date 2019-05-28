package com.purewhite.ywc.purewhitelibrary.view.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

public class WebLayout extends RelativeLayout {

    private WebView webView;
    private WebSettings settings;

    public WebView getWebView() {
        return webView;
    }

    public WebSettings getSettings() {
        return settings;
    }


    public WebLayout(Context context) {
        this(context, null);
    }

    public WebLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        webView = new WebView(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(webView, layoutParams);
        initSet();
    }

    private void initSet() {
        settings = webView.getSettings();
        //支持js
        settings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //是否使用缓存
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        // LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        // LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        // LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启 DOM storage API 功能
        settings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        settings.setDatabaseEnabled(true);
        //开启 Application Caches 功能
        settings.setAppCacheEnabled(true);
        //设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        // 支持屏幕缩放
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //设置可以访问文件
        settings.setAllowFileAccess(true);
        //支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("utf-8");
    }


    //释放资源
    public void onDestory() {
        removeAllViews();
        //释放资源
        webView.destroy();
        webView = null;
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

}