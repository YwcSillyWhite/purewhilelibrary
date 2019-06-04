package com.purewhite.ywc.purewhitelibrary.config;


import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

public final class SizeUtils {

    /**
     * dp转px
     */
    public static int dip2px(float dipValue) {
        final float scale = AppUtils.getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 获取屏幕的宽度
     */
    public static int getScreenWidth() {
        return AppUtils.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的高
     * @return
     */
    public static int getScreenHeight() {
        return AppUtils.getContext().getResources().getDisplayMetrics().heightPixels;
    }
}

