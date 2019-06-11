package com.purewhite.ywc.purewhitelibrary.window.popup.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class PopupUtils {

    private PopupWindow popupWindow;
    private View viewParent;
    private Context context;

    public static PopupUtils with(Context context,@LayoutRes int id)
    {
        return new PopupUtils(context,id);
    }

    private PopupUtils(Context context, @LayoutRes int id) {
        this.viewParent = LayoutInflater.from(context).inflate(id, null);
        this.context=context;
        this.popupWindow = new PopupWindow();
        this.popupWindow.setContentView(viewParent);
    }

    /**
     * 设置空间
     * @param width
     * @param height
     * @return
     */
    public PopupUtils setSpace(int width, int height)
    {
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        return this;
    }

    /**
     * 背景透明
     * @return
     */
    public PopupUtils setBackgoundLucency()
    {
        return setBackground(Color.TRANSPARENT);
    }

    /**
     * 设置背景
     * @return
     */
    public PopupUtils setBackground(int backColor)
    {
        popupWindow.setBackgroundDrawable(new ColorDrawable(backColor));
        return this;
    }

    /**
     * 设置PopupWindow是否能响应外部点击事件
     * @param touchable
     * @return
     */
    public PopupUtils setOutsideTouchable(boolean touchable)
    {
        popupWindow.setOutsideTouchable(touchable);
        return this;
    }

    /**
     * 设置PopupWindow是否能响应点击事件
     * @param touchable
     * @return
     */
    public PopupUtils setTouchable(boolean touchable)
    {
        popupWindow.setTouchable(touchable);
        return this;
    }


    public void show(View view)
    {
        popupWindow.showAsDropDown(view);
    }

    public void show(View view,int xOff,int yOff)
    {
        popupWindow.showAsDropDown(view,xOff,yOff);
    }

    public void show(View view,int xOff,int yOff,int gravity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            popupWindow.showAsDropDown(view,xOff,yOff,gravity);
        }
        else
        {
            popupWindow.showAsDropDown(view,xOff,yOff);
        }
    }

    public void show(ViewGroup viewGroup,int xOff,int yOff,int gravity)
    {
        popupWindow.showAtLocation(viewGroup,gravity,xOff,yOff);
    }

    public void dission()
    {
        if (popupWindow.isShowing())
        {
            popupWindow.dismiss();
        }
    }

    public void onDestory()
    {
        dission();
    }
}
