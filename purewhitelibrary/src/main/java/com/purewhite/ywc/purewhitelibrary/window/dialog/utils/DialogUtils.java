package com.purewhite.ywc.purewhitelibrary.window.dialog.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.StyleRes;

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
        return with(context,layoutId,R.style.BaseDialog);
    }


    public static DialogUtils withBack(Context context,@LayoutRes int layoutId)
    {
        return with(context,layoutId,R.style.BaseDialogNo);
    }



    public static DialogUtils with(Context context,@LayoutRes int layoutId,@StyleRes int theme)
    {
        return new DialogUtils(context,layoutId,theme);
    }


    public DialogUtils(Context context,@LayoutRes int layoutId,@StyleRes int theme) {
        super(context, layoutId, new Dialog(context,theme));
        dialog.setContentView(viewParent);
    }


    public DialogUtils setSplace(float scaleX,float scaleY)
    {
        return setSplace(scaleX,scaleY,Gravity.NO_GRAVITY);
    }


    public DialogUtils setSplace(float scaleX,float scaleY,int gravity)
    {
        return setSplace(scaleX,scaleY,0,0,gravity);
    }

    public DialogUtils setSplace(float scaleX,float scaleY,int x,int y,int gravity)
    {
        WindowManager.LayoutParams lp = window.getAttributes();
        if (scaleX>0&&scaleX<=1)
        {
            lp.width=(int)(SizeUtils.getScreenWidth()*scaleX);
        }
        if (scaleY>0&&scaleY<=1)
        {
            lp.height=(int)(SizeUtils.getScreenHeight()*scaleY);
        }
        if (x!=0)
        {
            lp.x=x;
        }
        if (y!=0)
        {
            lp.y=y;
        }
        lp.gravity=gravity;
        window.setAttributes(lp);
        return this;
    }

    public DialogUtils setSplace(int width,int height)
    {
        return setSplace(width,height,Gravity.NO_GRAVITY);
    }

    public DialogUtils setSplace(int width,int height,int gravity)
    {
        return setSplace(width,height,0,0,gravity);
    }


    public DialogUtils setSplace(int width,int height,int x,int y,int gravity)
    {
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = width;
        lp.height = height;
        if (x!=0)
        {
            lp.x=x;
        }
        if (x!=0)
        {
            lp.y=y;
        }
        lp.gravity=gravity;
        window.setAttributes(lp);
        return this;
    }

}
