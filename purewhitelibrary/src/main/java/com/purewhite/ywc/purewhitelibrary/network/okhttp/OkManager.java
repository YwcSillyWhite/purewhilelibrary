package com.purewhite.ywc.purewhitelibrary.network.okhttp;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.interceptor.ParamsInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author yuwenchao
 */
public class OkManager {

    /**
     * 设置拦截器
     * @return okhttpclient
     */
    public static OkHttpClient getOkHttp()
    {
        return getOkHttp(true);
    }

    public static OkHttpClient getOkHttp(boolean params)
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log拦截
        builder.addInterceptor(obtainLog());
        if (params)
        {
            //公共参数
            builder.addInterceptor(new ParamsInterceptor());
        }
        //时间设置  请求，读取，写入
        builder.readTimeout(10000,TimeUnit.MILLISECONDS);
        builder.writeTimeout(10000,TimeUnit.MILLISECONDS);
        builder.readTimeout(10000,TimeUnit.MILLISECONDS);
        return builder.build();
    }

    private static HttpLoggingInterceptor obtainLog()
    {
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
        return httpLoggingInterceptor;
    }
}
