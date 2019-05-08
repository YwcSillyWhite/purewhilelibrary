package com.purewhite.ywc.purewhitelibrary.network;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;

import java.util.Map;

/**
 * @author yuwenchao
 */
public class OkNetUtils {

    public static <T> void get(String url, Map<String, String> paramsRequest, OkCallBack<T> okCallBack)
    {
        OkHttpUtils.get(url,AppUtils.getContext(),paramsRequest).build().enqueue(okCallBack);
    }
}
