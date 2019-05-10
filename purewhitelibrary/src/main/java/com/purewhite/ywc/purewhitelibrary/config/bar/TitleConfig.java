package com.purewhite.ywc.purewhitelibrary.config.bar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

/**
 * 状态栏隐藏（不存在），设置标题的高度替代状态栏高度
 * @author yuwenchao
 */
public class TitleConfig {

    private static TitleConfig titleConfig;
    public static TitleConfig newInstance() {
        if (titleConfig==null)
        {
            synchronized (BarConfig.class)
            {
                if (titleConfig==null)
                {
                    titleConfig=new TitleConfig();
                }
            }
        }
        return titleConfig;
    }


    public TitleConfig setTitleBaeHeight(final View view)
    {
        if (view!=null)
        {
            //只有19之后
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
            {
                final int statusBarHeight = getStatusBarHeight();
                final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT ||
                        layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT) {
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            layoutParams.height = view.getHeight() + statusBarHeight;
                            view.setPadding(view.getPaddingLeft(),
                                    view.getPaddingTop() + statusBarHeight,
                                    view.getPaddingRight(),
                                    view.getPaddingBottom());
                        }
                    });
                } else {
                    layoutParams.height += statusBarHeight;
                    view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + statusBarHeight,
                            view.getPaddingRight(), view.getPaddingBottom());
                }
            }

        }
        return this;
    }



    public int getStatusBarHeight()
    {
        return  getInternalDimensionSize(AppUtils.getContext(), "status_bar_height");
    }


    private int getInternalDimensionSize(Context context, String key) {
        int result = 0;
        try {
            int resourceId = Resources.getSystem().getIdentifier(key, "dimen", "android");
            if (resourceId > 0) {
                int sizeOne = context.getResources().getDimensionPixelSize(resourceId);
                int sizeTwo = Resources.getSystem().getDimensionPixelSize(resourceId);

                if (sizeTwo >= sizeOne) {
                    return sizeTwo;
                } else {
                    float densityOne = context.getResources().getDisplayMetrics().density;
                    float densityTwo = Resources.getSystem().getDisplayMetrics().density;
                    return Math.round(sizeOne * densityTwo / densityOne);
                }
            }
        } catch (Resources.NotFoundException ignored) {
            return 0;
        }
        return result;
    }
}
