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


    public static Observable<ResponseBody> post(String url, Map<String, Object> paramsRequest)
    {
        RetrofitApi retrofitApi = RetrofitUtils.newInstance().create(RetrofitApi.class);
        return retrofitApi.post(url,paramsRequest);
    }



}
