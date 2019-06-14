package com.purewhite.ywc.frame.app;

import android.app.Application;
import android.content.Context;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

public class BaseApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
    }
}
