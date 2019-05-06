package com.purewhite.ywc.purewhitelibrary.network.retrofit.base;


import com.purewhite.ywc.purewhitelibrary.network.NetManager;

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
                    .client(NetManager.getOkHttp())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            map.put(baseUri,retrofit);
        }
        return retrofit;
    }






    public <T> T create(Class<T> service) {
        return create(NetManager.baseUri,service);
    }

    public <T> T create(String baseUri,Class<T> service)
    {
        return init(baseUri).create(service);
    }

}
