package com.purewhite.ywc.purewhitelibrary.app.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.R;
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
                }
                else
                {
                    Activity activity = (Activity) object;
                    activity.startActivityForResult(intent,requestCode);
                }

            }
            else
            {
                Context context = AppUtils.getContext();
                context.startActivity(intent);
            }
        }
    }








    public static void startActivityAnim(Activity activity)
    {
        if (activity!=null)
        {
            startActivityAnim(activity.getClass());
        }
    }


    public static void startActivityAnim(Class<?> cla)
    {
        startActivityAnim(cla,null,0);
    }


    public static void startActivityAnim(Class<?> cla,Bundle bundle)
    {
        startActivityAnim(cla,bundle,null,0);
    }

    public static void startActivityAnim(Class<?> cla,Object object,int requestCode)
    {
        startActivityAnim(cla,null,object,requestCode);
    }


    public static void startActivityAnim(Class<?> cla,Bundle bundle,Object object,int requestCode)
    {
        if (cla!=null)
        {
            Intent intent = new Intent(AppUtils.getContext(),cla);
            startActivityAnim(intent,bundle,object,requestCode);
        }
    }

    public static void startActivityAnim(Intent intent)
    {
        startActivityAnim(intent,null);
    }


    public static void startActivityAnim(Intent intent,Bundle bundle)
    {
        startActivityAnim(intent,bundle,null,0);
    }

    public static void startActivityAnim(Intent intent,Object object,int requestCode)
    {
        startActivityAnim(intent,null,object,requestCode);
    }


    public static void startActivityAnim(Intent intent,Bundle bundle,Object object,int requestCode)
    {
        startActivityAnim(intent,bundle,object,requestCode
                , R.anim.activity_open_enter
                , R.anim.activity_open_exit);
    }




    public static void startActivityAnim(Activity activity, @AnimRes int openEnter,@AnimRes int openEitx)
    {
        if (activity!=null)
        {
            startActivityAnim(activity.getClass(),openEnter,openEitx);
        }
    }


    public static void startActivityAnim(Class<?> cla, @AnimRes int openEnter,@AnimRes int openEitx)
    {
        startActivityAnim(cla,null,0,openEnter,openEitx);
    }


    public static void startActivityAnim(Class<?> cla,Bundle bundle, @AnimRes int openEnter,@AnimRes int openEitx)
    {
        startActivityAnim(cla,bundle,null,0,openEnter,openEitx);
    }

    public static void startActivityAnim(Class<?> cla,Object object,int requestCode,@AnimRes int openEnter,@AnimRes int openEitx)
    {
        startActivityAnim(cla,null,object,requestCode,openEnter,openEitx);
    }


    public static void startActivityAnim(Class<?> cla,Bundle bundle,Object object,int requestCode,@AnimRes int openEnter,@AnimRes int openEitx)
    {
        if (cla!=null)
        {
            Intent intent = new Intent(AppUtils.getContext(),cla);
            startActivityAnim(intent,bundle,object,requestCode,openEnter,openEitx);
        }
    }

    public static void startActivityAnim(Intent intent,@AnimRes int openEnter,@AnimRes int openEitx)
    {
        startActivityAnim(intent,null,openEnter,openEitx);
    }


    public static void startActivityAnim(Intent intent,Bundle bundle,@AnimRes int openEnter,@AnimRes int openEitx)
    {
        startActivityAnim(intent,bundle,null,0,openEnter,openEitx);
    }

    public static void startActivityAnim(Intent intent,Object object,int requestCode,@AnimRes int openEnter,@AnimRes int openEitx)
    {
        startActivityAnim(intent,null,object,requestCode,openEnter,openEitx);
    }


    public static void startActivityAnim(Intent intent,Bundle bundle,Object object,int requestCode,@AnimRes int openEnter,@AnimRes int openEitx)
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
                    if (context instanceof Activity)
                    {
                        ((Activity) context).overridePendingTransition(openEnter,openEitx);
                    }
                }
                else
                {
                    Activity activity = (Activity) object;
                    activity.startActivityForResult(intent,requestCode);
                    activity.overridePendingTransition(openEnter,openEitx);
                }

            }
            else
            {
                Context context = AppUtils.getContext();
                context.startActivity(intent);
                if (context instanceof Activity)
                {
                    ((Activity) context).overridePendingTransition(openEnter,openEitx);
                }
            }
        }
    }




}
