package com.purewhite.ywc.purewhitelibrary.network.retrofit;


import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkhttpBuilder;

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
    private Map<String,Retrofit> retrofitMap=new HashMap<>();
    private Map<String, OkHttpClient> okHttpClientMap=new HashMap<>();
    private OkhttpBuilder okhttpBuilder=new OkhttpBuilder();


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
    private Retrofit init(String baseUri,String key)
    {
        Retrofit retrofit = retrofitMap.get(baseUri);
        if (retrofit==null)
        {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(baseUri)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            OkHttpClient okHttpClient = okHttpClientMap.get(key);
            if (okHttpClient==null)
            {
                okHttpClient = okhttpBuilder.obtianClient(key);
                okHttpClientMap.put(key,okHttpClient);
            }
            final OkHttpClient ok=okHttpClient;
            if (ok!=null)
            {
                builder.client(ok);
            }
            else
            {
                throw new UnsupportedOperationException("okhttpclient can not null");
            }
            retrofit=builder.build();
            retrofitMap.put(baseUri,retrofit);
        }
        return retrofit;
    }


    public <T> T create(Class<T> service) {
        return create(AppUtils.baseUri,service);
    }

    public <T> T create(String baseUri,Class<T> service)
    {
        return create(baseUri,OkhttpBuilder.defaultOKhttp,service);
    }


    public <T> T create(String baseUri,String key,Class<T> service)
    {
        return init(baseUri,key).create(service);
    }

}
