package com.purewhite.ywc.purewhitelibrary.config;

import android.text.TextUtils;
import android.util.Log;

import com.purewhite.ywc.purewhitelibrary.BuildConfig;


/**
 * Log日志
 * @author yuwenchao
 */
public final class LogUtils {

    private static boolean debug=true;
    private static boolean error=true;

    private static final int ERROR=0;
    private static final int DEBUG=1;

    //请求数据参数
    public static void debug(String msg)
    {
        debug("",msg);
    }


    //请求数据参数
    public static void debug(String tag,String msg)
    {
        if (debug)
        {
            export(DEBUG,"Debug-"+tag,msg);
        }
    }


    //错误类型
    public static void error(String msg)
    {
        error("",msg);
    }

    public static void error(String tag,String msg)
    {
        if (error)
        {
            export(ERROR,"Error-"+tag,msg);
        }
    }

    private static void export(int type,String tag,String content)
    {
        if (TextUtils.isEmpty(content))
            return;
        if (BuildConfig.LOG)
        {
            switch (type)
            {
                case ERROR:
                    Log.e(tag,content);
                    break;
                case DEBUG:
                    Log.d(tag,content);
                    break;
            }
        }
    }



}
