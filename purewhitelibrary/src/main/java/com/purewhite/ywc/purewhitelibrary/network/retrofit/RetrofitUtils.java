package com.purewhite.ywc.purewhitelibrary.network.retrofit;


import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkManager;

import java.util.HashMap;
import java.util.Map;

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

    public static String baseUri="http://v2.api.haodanku.com";
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
            if (RetrofitUtils.baseUri.equals(baseUri))
            {
                retrofit=new Retrofit.Builder()
                        .baseUrl(baseUri)
                        .client(OkManager.getOkHttp())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
                map.put(baseUri,retrofit);
            }
            else
            {
                retrofit=new Retrofit.Builder()
                        .baseUrl(baseUri)
                        .client(OkManager.getOkHttp(false))
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
                map.put(baseUri,retrofit);
            }

        }
        return retrofit;
    }


    public <T> T create(Class<T> service) {
        return create(baseUri,service);
    }

    public <T> T create(String baseUri,Class<T> service)
    {
        return init(baseUri).create(service);
    }

}
