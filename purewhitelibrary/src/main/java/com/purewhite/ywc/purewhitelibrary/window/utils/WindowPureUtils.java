package com.purewhite.ywc.purewhitelibrary.window.utils;

import com.purewhite.ywc.purewhitelibrary.window.dialog.utils.BaseDialogUtils;
import com.purewhite.ywc.purewhitelibrary.window.popup.PopupWindowUtils;

/**
 * @author yuwenchao
 */
public class WindowPureUtils {


    public static void onDialogDestory(BaseDialogUtils...dialogUtils)
    {
        for (BaseDialogUtils dialogUtil:dialogUtils) {
            if (dialogUtil!=null)
            {
                dialogUtil.dismiss();
            }
        }
    }


    public static void onPopupDestory(PopupWindowUtils...popupWindowUtils)
    {
        for (PopupWindowUtils popupWindowUtil:popupWindowUtils) {
            if (popupWindowUtil!=null)
            {
                popupWindowUtil.dismiss();
            }
        }
    }




}
