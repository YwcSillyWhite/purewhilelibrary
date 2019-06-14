package com.purewhite.ywc.purewhitelibrary.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.network.retrofit.RetrofitUtils;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.RxDisposableManager;

import java.util.Stack;

/**
 *
 * @author yuwenchao
 * @date 2018/11/13
 */

public final class  AppUtils {
    private static Application application;
    private static Stack<Activity> stack=new Stack<>();
    private AppUtils() {

    }

    static Application.ActivityLifecycleCallbacks activityLifecycleCallbacks=new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            stack.add(activity);
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


    //初始化
    public static void init(Application application) {
        init(application,"http://v2.api.haodanku.com");
    }

    public static void init(Application application,String uri)
    {
        AppUtils.application =application;
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        if (!TextUtils.isEmpty(uri))
        {
            RetrofitUtils.baseUri=uri;
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
        return application;
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
