package com.purewhite.ywc.purewhitelibrary.window.dialog.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;
import com.purewhite.ywc.purewhitelibrary.window.dialog.base.WindowDialogUtils;


/**
 * dialogutils
 * @author yuwenchao
 */
public class DialogUtils extends WindowDialogUtils<DialogUtils> {


    public static DialogUtils with(Context context,@LayoutRes int id) {
        return with(context,R.style.BaseDialog,id);
    }

    public static DialogUtils with(Context context,@LayoutRes int id,ViewGroup.LayoutParams layoutParams) {
        return with(context,R.style.BaseDialog,id,layoutParams);
    }

    public static DialogUtils with(Context context,@StyleRes int theme,@LayoutRes int id) {
        return with(context,theme,id,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public static DialogUtils with(Context context,@StyleRes int theme
            ,@LayoutRes int id, ViewGroup.LayoutParams layoutParams) {
        return new DialogUtils(context,R.style.BaseDialog,id,layoutParams);
    }

    private DialogUtils(Context context, @StyleRes int theme,
                        @LayoutRes int id, ViewGroup.LayoutParams layoutParams)
    {
        this.context=context;
        this.dialog=new Dialog(context,theme);
        this.window=dialog.getWindow();
        this.viewParent = LayoutInflater.from(context).inflate(id, null);
        dialog.setContentView(viewParent,layoutParams);
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
