package com.purewhite.ywc.purewhitelibrary.window.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.LayoutRes;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.window.dialog.utils.BaseDialogUtils;
import com.purewhite.ywc.purewhitelibrary.window.dialog.utils.DialogUtils;

public class DialogBuilder {

    private int layoutId;
    private int wight = ViewGroup.LayoutParams.MATCH_PARENT;
    private int height = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int anim;
    private boolean canceledOnTouchOutside=true;
    private boolean canceled=true;
    private int themeRes= R.style.BaseDialog;
    private View.OnClickListener onClickListener;
    private DialogInterface.OnDismissListener onDismissListener;
    private DialogInterface.OnKeyListener onKeyListener;

    public DialogBuilder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public DialogBuilder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        this.onKeyListener = onKeyListener;
        return this;
    }

    public DialogBuilder setContentView(@LayoutRes int layoutId)
    {
        this.layoutId=layoutId;
        return this;
    }

    public DialogBuilder setWight(int wight) {
        this.wight = wight;
        return this;
    }

    public DialogBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public DialogBuilder setAnim(int anim) {
        this.anim = anim;
        return this;
    }

    public DialogBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public DialogBuilder setCanceled(boolean canceled) {
        this.canceled = canceled;
        return this;
    }

    public DialogBuilder setThemeRes(int themeRes) {
        this.themeRes = themeRes;
        return this;
    }

    public DialogBuilder setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public DialogUtils buildDialog(Context context)
    {
        View view = LayoutInflater.from(context).inflate(layoutId!=0?layoutId: R.layout.pure_window_error, null);
        Dialog dialog = new Dialog(context,themeRes);
        dialog.setContentView(view,new ViewGroup.LayoutParams(wight,height));
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.setCancelable(canceled);
        if (onDismissListener!=null)
        {
            dialog.setOnDismissListener(onDismissListener);
        }
        if (onKeyListener!=null)
        {
            dialog.setOnKeyListener(onKeyListener);
        }
        Window window = dialog.getWindow();
        if (anim!=0)
        {
            window.setWindowAnimations(anim);
        }
        return new DialogUtils(dialog,window,view,onClickListener);
    }


    public BaseDialogUtils buildBottom(Context context)
    {
        View view = LayoutInflater.from(context).inflate(layoutId!=0?layoutId: R.layout.pure_window_error, null);
        BottomSheetDialog dialog = new BottomSheetDialog(context, themeRes);
        dialog.setContentView(view,new ViewGroup.LayoutParams(wight,height));
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.setCancelable(canceled);
        if (onDismissListener!=null)
        {
            dialog.setOnDismissListener(onDismissListener);
        }
        if (onKeyListener!=null)
        {
            dialog.setOnKeyListener(onKeyListener);
        }
        if (anim!=0)
        {
            Window window = dialog.getWindow();
            window.setWindowAnimations(anim);
        }
        return new BaseDialogUtils(dialog,view,onClickListener);
    }
}
