package com.purewhite.ywc.purewhitelibrary.config;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;


/**
 * @author yuwenchao
 */
public class ToastUtils {
    private static Toast toast;
    public static void show(String content)
    {
        if (!TextUtils.isEmpty(content))
        {
            if (toast==null)
            {
                toast=Toast.makeText(AppUtils.getContext(),content,Toast.LENGTH_SHORT);
            }
            else
            {
                toast.setText(content);
            }
            toast.show();
        }
    }


    public static void show(@StringRes int res)
    {
        if (res!=0)
        {
            if (toast==null)
            {
                toast=Toast.makeText(AppUtils.getContext(),res,Toast.LENGTH_SHORT);
            }
            else
            {
                toast.setText(res);
            }
            toast.show();
        }
    }
}
