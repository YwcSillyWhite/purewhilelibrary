package com.purewhite.ywc.purewhitelibrary.config;


import android.text.TextUtils;
import android.widget.Toast;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;




/**
 * android x对吐司优化
 * @author yuwenchao
 */
public class ToastUtils {

    public static void show(String content)
    {
        show(content,Toast.LENGTH_SHORT);
    }


    public static void show(String content,int duration)
    {
        show(content,duration,0,0,0,false);
    }


    public static void show(String content,int duration,int gravity,int x,int y)
    {
        show(content,duration,gravity,x,y,true);
    }



    public static void show(String content,int duration,int gravity,int x,int y,boolean isGravity)
    {
        if (!TextUtils.isEmpty(content))
        {
            Toast toast = Toast.makeText(AppUtils.getContext(), "", duration);
            toast.setText(content);
            if (isGravity)
            {
                toast.setGravity(gravity,x,y);
            }
            toast.show();
        }
    }





}
