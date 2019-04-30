package com.purewhite.ywc.purewhitelibrary.config;

import android.widget.Toast;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;


public class ToastUtils {
    private static Toast toast;
    public static void show(String content)
    {
        if (toast==null)
        {
            toast=Toast.makeText(AppUtils.getContext(),null,Toast.LENGTH_SHORT);
        }
        toast.setText(content);
        toast.show();
    }


    public static void show(int res)
    {
        if (toast==null)
        {
            toast=Toast.makeText(AppUtils.getContext(),null,Toast.LENGTH_SHORT);
        }
        toast.setText(res);
        toast.show();
    }
}
