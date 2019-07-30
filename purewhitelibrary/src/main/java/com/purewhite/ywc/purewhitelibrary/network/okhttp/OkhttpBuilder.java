package com.purewhite.ywc.purewhitelibrary.network.okhttp;

import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.interceptor.ParamsInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpBuilder {
    public static final String defaultOKhttp="default";
    //长链接
    public static final String longLink="longLink";

    public final OkHttpClient obtianClient(String tag)
    {
        OkHttpClient okHttpClient=null;
        if (TextUtils.isEmpty(tag))
        {

        }
        else if (tag.equals(defaultOKhttp))
        {
            okHttpClient=defaultOkhttp();
        }
        else if (tag.equals(longLink))
        {
            okHttpClient=longLink();
        }
        else
        {
            okHttpClient=remainOkhttp();
        }
        return okHttpClient;
    }


    protected OkHttpClient defaultOkhttp()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.debug("okhttp",message);
            }
        });
        /**
         * NONE  :  没有log
         * BASEIC:  请求/响应行
         * HEADER:  请求/响应行 + 头
         * BODY  :  请求/响应航 + 头 + 体
         */
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //log拦截
        builder.addInterceptor(httpLoggingInterceptor);
        //时间设置  请求，读取，写入
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(10000,TimeUnit.MILLISECONDS);
        builder.readTimeout(10000,TimeUnit.MILLISECONDS);
        builder.addInterceptor(new ParamsInterceptor());
        return builder.build();
    }

    protected OkHttpClient longLink()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //时间设置  请求，读取，写入
        builder.readTimeout(3, TimeUnit.MILLISECONDS);
        builder.writeTimeout(3,TimeUnit.MILLISECONDS);
        builder.readTimeout(3,TimeUnit.MILLISECONDS);
        return builder.build();
    }


    protected OkHttpClient remainOkhttp()
    {
        return null;
    };
}
