package com.purewhite.ywc.purewhitelibrary.app.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

/**
 * activity跳转工作累
 * @author yuwenchao
 */
public class ActivitySkipUtils {


    public static void startActivity(Activity activity)
    {
        if (activity!=null)
        {
            startActivity(activity.getClass());
        }
    }


    public static void startActivity(Class<?> cla)
    {
        startActivity(cla,null,0);
    }


    public static void startActivity(Class<?> cla,Bundle bundle)
    {
        startActivity(cla,bundle,null,0);
    }

    public static void startActivity(Class<?> cla,Object object,int requestCode)
    {
        startActivity(cla,null,object,requestCode);
    }


    public static void startActivity(Class<?> cla,Bundle bundle,Object object,int requestCode)
    {
        if (cla!=null)
        {
            Intent intent = new Intent(AppUtils.getContext(),cla);
            startActivity(intent,bundle,object,requestCode);
        }
    }

    public static void startActivity(Intent intent)
    {
        startActivity(intent,null);
    }


    public static void startActivity(Intent intent,Bundle bundle)
    {
        startActivity(intent,bundle,null,0);
    }

    public static void startActivity(Intent intent,Object object,int requestCode)
    {
        startActivity(intent,null,object,requestCode);
    }


    public static void startActivity(Intent intent,Bundle bundle,Object object,int requestCode)
    {
        if (intent!=null)
        {
            if (bundle!=null)
            {
                intent.putExtras(bundle);
            }
            if (object!=null&&(object instanceof Fragment||object instanceof Activity))
            {
                if (object instanceof Fragment)
                {
                    Fragment fragment = (Fragment) object;
                    fragment.startActivityForResult(intent,requestCode);
                    Context context = AppUtils.getContext();
                }
                else
                {
                    Activity activity = (Activity) object;
                    activity.startActivityForResult(intent,requestCode);
                }

            }
            else
            {
                AppUtils.getContext().startActivity(intent);

            }
        }
    }
}
