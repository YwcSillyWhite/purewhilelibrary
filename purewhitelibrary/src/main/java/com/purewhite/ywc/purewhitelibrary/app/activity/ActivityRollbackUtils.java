package com.purewhite.ywc.purewhitelibrary.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

/**
 * activityrollback 回退
 * @author yuwenchao
 */
public class ActivityRollbackUtils {

    public static void finish()
    {
        Activity activity = AppUtils.obtainTopActivity();
        if (activity!=null)
        {
            finish(activity);
        }
    }

    public static void finish(Activity activity)
    {
        finish(activity,null);
    }

    public static void finish(Activity activity,Integer requestCode)
    {
        finish(activity,requestCode,null);
    }

    public static void finish(Activity activity,Integer requestCode,Bundle bundle)
    {
        if (requestCode!=null)
        {
            Intent intent = new Intent();
            if (bundle!=null)
            {
                intent.putExtras(bundle);
            }
            activity.setResult(requestCode,intent);
        }
        activity.finish();
    }
}
