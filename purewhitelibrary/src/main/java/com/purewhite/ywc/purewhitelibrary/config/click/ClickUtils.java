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

    /**
     *
     * @param view
     * @param time
     * @return 返回值false不可以点击，true可以点击
     */
    public static boolean  clickable(View view,int time)
    {
        Object tag = view.getTag(view.getId());
        if (tag==null)
        {
            long newTime = System.currentTimeMillis();
            view.setTag(view.getId(),newTime);
        }
        else
        {
            //如果这个view已存其他tag，那么去除点击间隔
            if (tag instanceof Long)
            {
                long oldTime= ((Long) tag);
                long newTime = System.currentTimeMillis();
                if (newTime-oldTime>time)
                {
                    view.setTag(view.getId(),newTime);
                }
                else
                {
                    return false;
                }
            }
        }
        return true;
    }
}
