package com.purewhite.ywc.purewhitelibrary.network;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;

import java.util.Map;

/**
 * @author yuwenchao
 */
public class OkNetUtils {


    public static <T> void get(Activity activity,String url,Map<String,String> paramsRequest,OkCallBack<T> okCallBack)
    {
        OkHttpUtils.get().addParams(paramsRequest)
                .tag(activity)
                .url(url)
                .enqueue(okCallBack);
    }

    public static <T> void get(Fragment fragment, String url, Map<String,String> paramsRequest, OkCallBack<T> okCallBack)
    {
        OkHttpUtils.get().addParams(paramsRequest)
                .tag(fragment)
                .url(url)
                .enqueue(okCallBack);

    }

}
