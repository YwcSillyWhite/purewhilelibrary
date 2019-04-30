package com.purewhite.ywc.frame1.app;

import android.app.Application;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
    }
}
