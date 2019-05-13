package com.purewhite.ywc.purewhitelibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @author yuwenchao
 */
public class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(Context context, boolean cancelable,DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }







}
