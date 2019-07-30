package com.purewhite.ywc.frame.app;

import android.app.Application;

import com.example.tbslibrary.app.TbsAppUtils;
import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

import java.util.HashMap;
import java.util.Map;

public class BaseApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //纯白框架
        initPure();
        //tx x5
        initTbs();
    }

    private void initTbs() {
        TbsAppUtils.initLibrary(this);
    }

    private void initPure() {

        AppUtils.initLibrary(this,"",375);
    }
}
