package com.purewhite.ywc.purewhitelibrary.app.activity;

import com.purewhite.ywc.purewhitelibrary.R;

/**
 *
 * @author yuwenchao
 * @date 2018/11/13
 */
public class ActivityUtils {


    public  static StartBuilder start()
    {
        return new StartBuilder();
    }

    public static StartBuilder startDefalut()
    {
        return start().with(R.anim.activity_left_in);
    }

    public static FinishBuilder finish()
    {
        return new FinishBuilder();
    }


    public static FinishBuilder finishDefalut()
    {
        return new FinishBuilder().with(R.anim.activity_left_out);
    }


}
