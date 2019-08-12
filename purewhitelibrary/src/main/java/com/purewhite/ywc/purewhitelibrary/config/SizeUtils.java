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


    public static float dpToPxFloat(float dp)
    {
        final float scale = AppUtils.getContext().getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
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




    //代码宽适配
    public static int widthAdaptive(int sunWidth,int size)
    {
        int screenWidth = getScreenWidth();
        return ((int) (screenWidth * size * 1.0 / sunWidth));
    }


    public static int heightdaptive(int sunHeight,int size)
    {
        int screenHeight = getScreenHeight();
        return ((int) (screenHeight * size * 1.0 / sunHeight));
    }
}

