package com.purewhite.ywc.frame.app;

import android.app.Application;
import android.content.Context;

import com.example.tbslibrary.app.TbsAppUtils;
import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

import java.util.HashMap;
import java.util.Map;

public class BaseApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initPure();
        initTbs();

    }

    private void initTbs() {
        TbsAppUtils.initLibrary(this);
    }

    private void initPure() {
        Map<String,String> map=new HashMap<>();
        map.put("apikey","pureWhite");
        AppUtils.initLibrary(this,"",0,map);
    }
}
