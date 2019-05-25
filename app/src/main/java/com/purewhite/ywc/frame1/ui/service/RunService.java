package com.purewhite.ywc.frame1.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

public class RunService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.debug("service","onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.debug("service","onDestroy");
    }
}
