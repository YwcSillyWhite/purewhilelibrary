package com.purewhite.ywc.purewhitelibrary.config.click;

import android.view.View;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

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
        long oldTime=0;
        if (tag instanceof Long)
        {
            oldTime= ((Long) tag);
        }
        long newTime = System.currentTimeMillis();
        LogUtils.debug("new_time",oldTime+","+newTime);
        if (newTime-oldTime>=time)
        {
            LogUtils.debug("new_time","can click");
            view.setTag(view.getId(),newTime);
            return true;
        }
        return false;
    }
}
