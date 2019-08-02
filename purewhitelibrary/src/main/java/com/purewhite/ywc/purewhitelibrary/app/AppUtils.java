package com.purewhite.ywc.purewhitelibrary.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.config.AdaptiveUtils;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxDisposableManager;
import com.squareup.leakcanary.LeakCanary;

import java.util.Stack;

import io.reactivex.annotations.NonNull;

/**
 *
 * @author yuwenchao
 * @date 2018/11/13
 */

public final class  AppUtils {
    //默认适配宽度是360dp
    public static int adaptiveWightDp=375;
    //retrofit默认请求接口
    public static String baseUri="http://v2.api.haodanku.com";
    private static Application application;
    private static Stack<Activity> stack=new Stack<>();

    private AppUtils() {

    }

    static Application.ActivityLifecycleCallbacks activityLifecycleCallbacks=new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            stack.add(activity);
            AdaptiveUtils.adaptiveWidth(activity, application,adaptiveWightDp);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            stack.remove(activity);
            if (stack.isEmpty())
            {
                RxDisposableManager.getInstance().clear();
            }

        }
    };


    /**
     * 框架初始化
     * @param application
     * @param retrofitBaseUri   retrofit默认框架
     * @param adaptiveWight     屏幕适配的dp
     * @Param map     okHttp公共参数
     */
    public static void initLibrary(@NonNull Application application, String retrofitBaseUri, int adaptiveWight)
    {

        if (!TextUtils.isEmpty(retrofitBaseUri))
        {
            baseUri=retrofitBaseUri;

        }
        if (adaptiveWight>0)
        {
            adaptiveWightDp=adaptiveWight;
        }
        AppUtils.application =application;
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);


        //内存检测
        if (!LeakCanary.isInAnalyzerProcess(application)) {
            LeakCanary.install(application);
        }

    }

    public static Activity getTopActivity()
    {
        if (stack.isEmpty())
        {
            //peek()函数返回栈顶的元素，但不弹出该栈顶元素。
            //pop()函数返回栈顶的元素，并且将该栈顶元素出栈。
            return stack.peek();
        }
        return null;
    }

    public static Context getContext()
    {
        return application.getApplicationContext();
    }



    /**
     * 关闭所有的activity
     */
    public static void closeApp()
    {
        if (stack.isEmpty())
        {
            for (Activity activity:stack) {
                activity.finish();
            }
        }
    }

}
