package com.purewhite.ywc.purewhitelibrary.window.utils;

import com.purewhite.ywc.purewhitelibrary.window.base.WindowDialogUtils;
import com.purewhite.ywc.purewhitelibrary.window.popup.utils.PopupUtils;

/**
 * @author yuwenchao
 */
public class WindowPureUtils {


    public static void onDialogDestory(WindowDialogUtils...wds)
    {
        for (WindowDialogUtils windowUtils:wds) {
            if (windowUtils!=null)
            {
                windowUtils.onDestroy();
            }
        }
    }


    public static void onPopupDestory(PopupUtils ...pus)
    {
        for (PopupUtils popupUtils:pus) {
            if (popupUtils!=null)
            {
                popupUtils.onDestory();
            }
        }
    }
}
