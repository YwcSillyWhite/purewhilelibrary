package com.purewhite.ywc.purewhitelibrary.network;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

import okhttp3.logging.HttpLoggingInterceptor;

public class NetManager {
    /**
     * http://v2.api.haodanku.com
     */
    public static String baseUri="";

    public static HttpLoggingInterceptor obtainLog()
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
