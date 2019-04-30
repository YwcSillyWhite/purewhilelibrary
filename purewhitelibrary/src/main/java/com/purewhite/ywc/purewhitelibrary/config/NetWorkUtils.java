package com.purewhite.ywc.purewhitelibrary.config;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;


/**
 * @author yuwenchao
 */
public class NetWorkUtils {

    //获取网络信息对象
    private static NetworkInfo getActiveNetworkInfo() {
        return ((ConnectivityManager) AppUtils.getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
    }

    //判断是否存在网络
    public static boolean isConnected() {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

}
