package com.example.tbslibrary.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

public final class TbsAppUtils {

    private static Application application;
    private static final QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

        @Override
        public void onViewInitFinished(boolean arg0) {
            // TODO Auto-generated method stub
            //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
            Log.d("app", " onViewInitFinished is " + arg0);
        }

        @Override
        public void onCoreInitFinished() {
            // TODO Auto-generated method stub
        }
    };

    //初始化
    public static void initLibrary(Application application)
    {
        TbsAppUtils.application=application;
        //x5内核初始化接口
        QbSdk.initX5Environment(application.getApplicationContext(),  cb);
    }


    //获取getApplicationContext
    public static Context getContext()
    {
        return application.getApplicationContext();
    }


    //获取getApplication
    public static Application getApplication()
    {
        return application;
    }
}
