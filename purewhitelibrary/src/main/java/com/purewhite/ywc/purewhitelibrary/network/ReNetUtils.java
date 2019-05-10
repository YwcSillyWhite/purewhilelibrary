package com.purewhite.ywc.purewhitelibrary.network;

import com.google.gson.Gson;
import com.purewhite.ywc.purewhitelibrary.network.retrofit.RetrofitApi;
import com.purewhite.ywc.purewhitelibrary.network.retrofit.RetrofitUtils;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.HttpObserver;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxSchedulers;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * @author yuwenchao
 */
public class ReNetUtils {

    public static Observable<ResponseBody> get(String url, Map<String, Object> paramsRequest)
    {
        RetrofitApi retrofitApi = RetrofitUtils.newInstance().create(RetrofitApi.class);
        if (paramsRequest!=null)
        {
            return retrofitApi.get(url,paramsRequest);
        }
        return retrofitApi.get(url);
    }

    public static void get(String url, Map<String, Object> paramsRequest,HttpObserver<ResponseBody> httpObserver)
    {
        get(url,paramsRequest).compose(RxSchedulers.<ResponseBody>ioToMain()).subscribe(httpObserver);
    }


    public static <T> Observable<T> get(String url, Map<String, Object> paramsRequest, final Class<T> cls)
    {
        return get(url,paramsRequest).map(new Function<ResponseBody, T>() {
            @Override
            public T apply(ResponseBody responseBody) throws Exception {
                return new Gson().fromJson(responseBody.string(),cls);
            }
        });
    }


    public static <T>void get(String url, Map<String, Object> paramsRequest, Class<T> cls
            , HttpObserver<T> httpObserver)
    {
        get(url,paramsRequest,cls).compose(RxSchedulers.<T>ioToMain()).subscribe(httpObserver);
    }



    public static <T> Observable<T> getT(String url, Map<String,Object> paramsRequest, final T t)
    {
        return (Observable<T>) get(url,paramsRequest,t.getClass());
    }

    public static <T> void getT(String url, Map<String, Object> paramsRequest,T t
            , HttpObserver<T> httpObserver)
    {
        getT(url,paramsRequest,t).compose(RxSchedulers.<T>ioToMain()).subscribe(httpObserver);
    }



}
