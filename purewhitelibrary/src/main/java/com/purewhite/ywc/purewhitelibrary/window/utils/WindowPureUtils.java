package com.purewhite.ywc.purewhitelibrary.window.utils;

import com.purewhite.ywc.purewhitelibrary.window.base.WindowDialogUtils;

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


}
