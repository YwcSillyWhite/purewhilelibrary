package com.purewhite.ywc.purewhitelibrary.view.dialog.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.app.activity.ActivityRollbackUtils;
import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;
import com.purewhite.ywc.purewhitelibrary.view.dialog.base.WindowUtils;


/**
 * @author yuwenchao
 */
public class DialogUtils extends WindowUtils<DialogUtils> {

    private Context context;
    public  Dialog dialog;
    private final Window window;



    public static DialogUtils with(Context context) {
        return with(context,R.style.BaseDialog);
    }

    public static DialogUtils with(Context context,@StyleRes int theme) {

        return new DialogUtils(context,R.style.BaseDialog);
    }

    private DialogUtils(Context context,@StyleRes int theme)
    {
        this.context=context;
        dialog=new Dialog(context,theme);
        window=dialog.getWindow();
    }




    public DialogUtils(Context context,@LayoutRes int layoutId,ViewGroup.LayoutParams layoutParams,@StyleRes int res)
    {
        this.context=context;
        viewParent = LayoutInflater.from(context).inflate(layoutId, null);
        dialog = new Dialog(context,res);
        window=dialog.getWindow();
        dialog.setContentView(viewParent,layoutParams);
    }

    @Override
    public void show() {
        if (!dialog.isShowing())
        {
            dialog.show();
        }
    }

    @Override
    public void dismiss() {
        if (dialog.isShowing())
        {
            dialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        if (dialog!=null)
        {
            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }

    @Override
    public DialogUtils addLayout(int id) {
        return addLayout(id,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public DialogUtils addLayout(int id, ViewGroup.LayoutParams layoutParams) {
        View view = LayoutInflater.from(context).inflate(id, null);
        dialog.setContentView(view,layoutParams);
        viewParent=view;
        return this;
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

    @Override
    public DialogUtils addAnim(int resId) {
        window.setWindowAnimations(resId);
        return this;
    }

    @Override
    public DialogUtils setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return this;
    }

    @Override
    public DialogUtils bindActivity(final Activity activity) {
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // 关闭 Dialog
                    dialog.dismiss();
                    // 关闭当前 Activity
                    ActivityRollbackUtils.finish(activity);
                    // 返回 true，表示返回事件已被处理，不再向下传递
                    return true;
                } else {
                    return false;
                }
            }
        });
        return this;
    }
}
