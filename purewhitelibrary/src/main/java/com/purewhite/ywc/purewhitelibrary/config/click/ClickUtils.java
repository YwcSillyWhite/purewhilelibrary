package com.purewhite.ywc.purewhitelibrary.config.click;

import android.view.View;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 * 防多次点击的
 */

public final class ClickUtils {

    public static boolean  clickable(View view)
    {
        return clickable(view,600);
    }

    public static boolean  clickable(View view,int time)
    {
        Object tag = view.getTag(view.getId());
        long oldTime=tag!=null? ((long) tag):0;
        long newTime = System.currentTimeMillis();
        if (newTime-oldTime>=time)
        {
            view.setTag(view.getId(),newTime);
            return true;
        }
        return false;
    }
}
