package com.purewhite.ywc.purewhitelibrary.window.dialog.bottomsheet;

import android.content.Context;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.window.base.WindowDialogUtils;


/**
 * @author yuwenchao
 */
public class BottomSheetUtils extends WindowDialogUtils<BottomSheetUtils> {


    public static BottomSheetUtils with(Context context, int layoutId)
    {
        return with(context,layoutId, R.style.BaseDialog);
    }

    public static BottomSheetUtils with(Context context, int layoutId,int theme)
    {
        return new BottomSheetUtils(context,layoutId,theme);
    }

    public BottomSheetUtils(Context context, int layoutId,int theme) {
        super(context, layoutId,new BottomSheetDialog(context,theme));
        dialog.setContentView(viewParent);
    }
}
