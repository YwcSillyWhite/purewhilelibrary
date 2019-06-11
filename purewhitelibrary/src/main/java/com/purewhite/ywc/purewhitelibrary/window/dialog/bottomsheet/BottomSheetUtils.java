package com.purewhite.ywc.purewhitelibrary.window.dialog.bottomsheet;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.window.dialog.base.WindowDialogUtils;

/**
 * @author yuwenchao
 */
public class BottomSheetUtils extends WindowDialogUtils<BottomSheetUtils> {

    public static BottomSheetUtils with(Context context, @LayoutRes int id) {
        return with(context,R.style.BaseDialog,id);
    }

    public static BottomSheetUtils with(Context context,@StyleRes int theme,@LayoutRes int id) {
        return new BottomSheetUtils(context,theme,id);
    }

    private BottomSheetUtils(Context context, @StyleRes int theme, @LayoutRes int id)
    {
        this.context=context;
        this.dialog=new BottomSheetDialog(context, theme);
        this.window=dialog.getWindow();
        this.viewParent = LayoutInflater.from(context).inflate(id, null);
        dialog.setContentView(viewParent);
    }

}
