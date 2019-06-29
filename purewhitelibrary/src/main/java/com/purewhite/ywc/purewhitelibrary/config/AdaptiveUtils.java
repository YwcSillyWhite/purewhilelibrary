package com.purewhite.ywc.purewhitelibrary.config;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import io.reactivex.annotations.NonNull;

/**
 * 适配工具类，本类是基于今日头条适配进行封装的
 *
 *
 * dip /dp      ： Density independent pixels ，设备无关像素。
 * px           ： 像素
 * dpi          ：dots per inch ， 直接来说就是一英寸多少个像素点。常见取值 120，160，240。我一般称作像素密度，简称密度
 * density      ：  其实是 DPI / (160像素/英寸) 后得到的值
 *
 * px = density * dp;
 * density = dpi / 160;
 * px = dp * (dpi / 160);
 *
 *
 *
 *
 */
public class AdaptiveUtils {


    private static float appDensity;
    private static float appScaledDensity;
    public static void adaptiveWidth(@NonNull Activity activity, @NonNull final Application application, int dip)
    {
        final DisplayMetrics appDisplayMetrics =application.getResources().getDisplayMetrics();
        if (appDensity==0)
        {
            appDensity=appDisplayMetrics.density;
            appScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig!=null&&newConfig.fontScale>0)
                    {
                        appScaledDensity=application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        //appDisplayMetrics.widthPixels 宽度的px  求出以dp下的density
        float targetDensity = appDisplayMetrics.widthPixels * 1.0f / dip;
        float targetScaleDentisty = targetDensity * appScaledDensity / appDensity;
        int targetDpi = (int) (targetDensity * 160);
        appDisplayMetrics.density=targetDensity;
        appDisplayMetrics.scaledDensity=targetScaleDentisty;
        appDisplayMetrics.densityDpi=targetDpi;


        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density=targetDensity;
        activityDisplayMetrics.scaledDensity=targetScaleDentisty;
        activityDisplayMetrics.densityDpi=targetDpi;

    }
}
