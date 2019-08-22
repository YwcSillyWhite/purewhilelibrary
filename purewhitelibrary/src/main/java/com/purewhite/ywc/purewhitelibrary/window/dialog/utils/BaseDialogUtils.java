package com.purewhite.ywc.purewhitelibrary.window.dialog.utils;

import android.app.Dialog;
import android.view.View;

import com.purewhite.ywc.purewhitelibrary.window.base.WindowViewUtils;

public class BaseDialogUtils<T extends BaseDialogUtils> extends WindowViewUtils<T> {


    private Dialog dialog;
    public BaseDialogUtils(Dialog dialog, View view, View.OnClickListener onClickListener) {
        super(view, onClickListener);
        this.dialog=dialog;
    }


    public void show()
    {
        if (dialog!=null&&!dialog.isShowing());
        {
            dialog.show();
        }
    }


    public void dismiss()
    {
        if (dialog!=null&&dialog.isShowing())
        {
            dialog.dismiss();
        }
    }


}
