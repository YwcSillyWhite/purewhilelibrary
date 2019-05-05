package com.purewhite.ywc.purewhitelibrary.network.retrofit.base;



import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.network.BaseUri;
import com.purewhite.ywc.purewhitelibrary.network.retrofit.base.interceptor.ParamsInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author yuwenchao
 * @date 2018/11/20
 */

public class BaseRetrofit {
    //好单库api接口

    private static BaseRetrofit basRetrofit;
    private Map<String,Retrofit> map=new HashMap<>();

    public static BaseRetrofit newInstance() {
        if (basRetrofit==null)
        {
            synchronized (BaseRetrofit.class)
            {
                if (basRetrofit==null)
                {
                    basRetrofit=new BaseRetrofit();
                }
            }
        }
        return basRetrofit;
    }

    //初始化
    private Retrofit init(String baseUri)
    {
        Retrofit retrofit = map.get(baseUri);
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(baseUri)
                    .client(getOkHttp())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            map.put(baseUri,retrofit);
        }
        return retrofit;
    }


    /**
     * 设置拦截器
     * @return okhttpclient
     */
    private OkHttpClient getOkHttp()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log拦截
        builder.addInterceptor(obtainLog());
        //参数拦截
        builder.addInterceptor(new ParamsInterceptor());

        //时间设置  请求，读取，写入
        builder.readTimeout(10000,TimeUnit.MILLISECONDS);
        builder.writeTimeout(10000,TimeUnit.MILLISECONDS);
        builder.readTimeout(10000,TimeUnit.MILLISECONDS);

        return builder.build();
    }



    private HttpLoggingInterceptor obtainLog()
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





    public <T> T create(Class<T> service) {
        return create(BaseUri.baseUri,service);
    }

    public <T> T create(String baseUri,Class<T> service)
    {
        return init(baseUri).create(service);
    }







}
