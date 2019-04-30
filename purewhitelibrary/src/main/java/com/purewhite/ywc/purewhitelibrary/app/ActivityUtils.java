package com.purewhite.ywc.purewhitelibrary.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 *
 * @author yuwenchao
 * @date 2018/11/13
 */
public class ActivityUtils {

    public static void startActivity(Class<?> cls)
    {
        startActivity(cls,null);
    }
    public static void startActivity(Class<?> cls, Bundle bundle)
    {
        startActivity(cls,bundle,null,0);
    }

    public static void startActivity(Class<?> cls, Bundle bundle,Context context,int requestCode)
    {
        if (cls==null) {
            return;
        }
        Intent intent=new Intent(AppUtils.getContext(),cls);
        if (bundle!=null) {
            intent.putExtras(bundle);
        }
        startActivity(intent,context,requestCode);
    }

    public static void startActivity(Intent intent)
    {
        startActivity(intent,null,0);
    }

    public static void startActivity(Intent intent, Context context,int requestCode)
    {
        if (context!=null&&context instanceof Activity)
        {
            ((Activity) context).startActivityForResult(intent,requestCode);
        }
        else
        {
            AppUtils.getContext().startActivity(intent);
        }
    }



    //跳转唯一商品activity
    public static void startSoleActivity(Intent intent,String id)
    {
        AppUtils.removeActivity(id);
        AppUtils.getContext().startActivity(intent);
    }




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
