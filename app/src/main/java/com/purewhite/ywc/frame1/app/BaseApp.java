package com.purewhite.ywc.frame1.app;

import android.app.Application;

import com.purewhite.ywc.frame1.config.ServiceUtils;
import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this,"http://v2.api.haodanku.com");
        ServiceUtils.startProtectService(this);
    }
}
