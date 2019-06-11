package com.purewhite.ywc.purewhitelibrary.window.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.KeyEvent;
import android.view.Window;

/**
 * @author yuwenchao
 */
public class WindowDialogUtils<T extends WindowViewUtils> extends WindowViewUtils<T>{
    protected Dialog dialog;
    protected Window window;

    public WindowDialogUtils(Context context,@LayoutRes int layoutId, Dialog dialog) {
        super(context,layoutId);
        this.dialog=dialog;
        this.window=dialog.getWindow();
    }

    /**
     * setCancelable 默认是true ，是点击返回dialog销毁，false就是不销毁
     * @return
     */
    public T setCancelable(boolean cancelable)
    {
        dialog.setCancelable(cancelable);
        return ((T) this);
    }


    /**
     * 绑定activity
     * @param activity
     * @return
     */
    public T bindActivity(@NonNull final Activity activity)
    {
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // 关闭 Dialog
                    dialog.dismiss();
                    // 关闭当前 Activity
                    activity.finish();
                    // 返回 true，表示返回事件已被处理，不再向下传递
                    return true;
                } else {
                    return false;
                }
            }
        });
        return ((T) this);
    }


    /**
     *  //添加动画
     * @param resId
     * @return
     */
    public T addAnim(@StyleRes int resId) {
        window.setWindowAnimations(resId);
        return ((T) this);
    }





    /**
     * 显示
     */
    public void show()
    {
        if (!dialog.isShowing())
        {
            dialog.show();
        }
    }


    /**
     * 隐藏
     */
    public void dismiss()
    {
        if (dialog.isShowing())
        {
            dialog.dismiss();
        }
    }


    /**
     * 隐藏
     */
    public void onDestroy()
    {
        dismiss();
    }
}
