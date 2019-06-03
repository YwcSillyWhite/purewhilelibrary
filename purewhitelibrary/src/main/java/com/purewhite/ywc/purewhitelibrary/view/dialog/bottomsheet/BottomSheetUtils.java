package com.purewhite.ywc.purewhitelibrary.view.dialog.bottomsheet;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.design.widget.BottomSheetDialog;
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
public class BottomSheetUtils extends WindowUtils<BottomSheetUtils> {

    private final BottomSheetDialog bottomSheetDialog;
    private Context context;
    private Window window;

    public static BottomSheetUtils with(Context context) {
        return with(context,R.style.BaseDialog);
    }

    public static BottomSheetUtils with(Context context,int theme) {
        return new BottomSheetUtils(context,theme);
    }

    private BottomSheetUtils(Context context,int theme) {
        bottomSheetDialog = new BottomSheetDialog(context, theme);
        this.context=context;
        this.window=bottomSheetDialog.getWindow();
    }


    @Override
    public void show() {
       if (!bottomSheetDialog.isShowing())
       {
           bottomSheetDialog.show();
       }
    }

    @Override
    public void dismiss() {
        if (bottomSheetDialog.isShowing())
        {
            bottomSheetDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        dismiss();
    }

    @Override
    public BottomSheetUtils addLayout(int id) {
        return addLayout(id,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public BottomSheetUtils addLayout(int id, ViewGroup.LayoutParams layoutParams) {
        View view = LayoutInflater.from(context).inflate(id, null);
        bottomSheetDialog.setContentView(view,layoutParams);
        viewParent=view;
        return this;
    }



    @Override
    public BottomSheetUtils addAnim(int resId) {
        window.setWindowAnimations(resId);
        return this;
    }

    @Override
    public BottomSheetUtils setCancelable(boolean cancelable) {
        bottomSheetDialog.setCancelable(cancelable);
        return this;
    }

    @Override
    public BottomSheetUtils bindActivity(final Activity activity) {
        bottomSheetDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
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
