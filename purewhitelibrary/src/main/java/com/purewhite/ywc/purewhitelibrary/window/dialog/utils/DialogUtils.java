package com.purewhite.ywc.purewhitelibrary.window.dialog.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;
import com.purewhite.ywc.purewhitelibrary.window.base.WindowDialogUtils;

/**
 * dialogutils
 * @author yuwenchao
 * R.style.BaseDialog
 */
public class DialogUtils extends WindowDialogUtils<DialogUtils> {


    public static DialogUtils with(Context context,@LayoutRes int layoutId)
    {
        return with(context,layoutId,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static DialogUtils with(Context context,@LayoutRes int layoutId,int width,int height)
    {
        return with(context,layoutId,R.style.BaseDialog,width,height);
    }

    public static DialogUtils with(Context context,@LayoutRes int layoutId,@StyleRes int theme
            ,int width,int height)
    {
        return new DialogUtils(context,layoutId,theme,width,height);
    }

    public DialogUtils(Context context,@LayoutRes int layoutId,@StyleRes int theme,int width,int height) {
        super(context, layoutId, new Dialog(context,theme));
        dialog.setContentView(viewParent,new ViewGroup.LayoutParams(width,height));
    }





    public DialogUtils setScreenWidth(float scale) {
        if (scale>0&&scale<=1)
        {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width =(int)(SizeUtils.getScreenWidth()*scale);
            window.setAttributes(lp);
        }
        return this;
    }




    public DialogUtils setScreenHight(float scale) {
        if (scale>0&&scale<=1)
        {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.height =(int)(SizeUtils.getScreenHeight()*scale);
            window.setAttributes(lp);
        }
        return this;
    }


    public DialogUtils setGravity(int gravity) {
        window.setGravity(gravity);
        return this;
    }


    public DialogUtils setLayout(int width, int height)
    {
        window.setLayout(width,height);
        return this;
    }
}
