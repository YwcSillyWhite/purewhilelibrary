package com.purewhite.ywc.purewhitelibrary.config.bar;

import android.app.Activity;
import android.view.View;
import android.view.Window;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态栏参数设置
 * @author yuwenchao
 */
public class BarConfig {

    private static BarConfig barConfig;
    private Map<String,Window> windowMap=new HashMap<>();
    private Window window;

    public static BarConfig newInstance() {
        if (barConfig==null)
        {
            synchronized (BarConfig.class)
            {
                if (barConfig==null)
                {
                    barConfig=new BarConfig();
                }
            }
        }
        return barConfig;
    }

    public BarConfig with(Activity activity)
    {
        Window window = windowMap.get(activity.toString());
        if (window==null)
        {
            window = activity.getWindow();
            windowMap.put(activity.toString(),window);
        }
        this.window=window;
        return this;
    }


    //设置文本颜色
    public void setStatusBarTextColor(boolean dark)
    {
        View decorView = window.getDecorView();
        if (dark) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }







}
