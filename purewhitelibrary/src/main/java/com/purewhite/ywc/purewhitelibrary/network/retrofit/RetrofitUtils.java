package com.purewhite.ywc.purewhitelibrary.network.retrofit;


import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkManager;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.interceptor.ParamsInterceptor;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author yuwenchao
 * @date 2018/11/20
 */

public class RetrofitUtils {
    //好单库api接口
    private static RetrofitUtils basRetrofit;
    private Map<String,Retrofit> map=new HashMap<>();

    public static RetrofitUtils newInstance() {
        if (basRetrofit==null)
        {
            synchronized (RetrofitUtils.class)
            {
                if (basRetrofit==null)
                {
                    basRetrofit=new RetrofitUtils();
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
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(baseUri)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            if (AppUtils.baseUri.equals(baseUri))
            {
                builder.client(OkManager.obtainBuilder().addInterceptor(new ParamsInterceptor()).build());
            }
            else
            {
                builder.client(OkManager.obtainBuilder().build());
            }
            retrofit=builder.build();
            map.put(baseUri,retrofit);
        }
        return retrofit;
    }


    public <T> T create(Class<T> service) {
        return create(AppUtils.baseUri,service);
    }

    public <T> T create(String baseUri,Class<T> service)
    {
        return init(baseUri).create(service);
    }

}
