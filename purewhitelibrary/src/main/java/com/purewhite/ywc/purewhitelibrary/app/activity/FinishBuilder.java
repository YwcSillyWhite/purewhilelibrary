package com.purewhite.ywc.purewhitelibrary.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class FinishBuilder {


    private List<Integer> list;
    public FinishBuilder with(@AnimRes int ex)
    {
        if (list==null)
        {
            list=new ArrayList<>();
        }
        list.add(ex);
        return this;
    }

    public  void finish()
    {
        Activity activity = AppUtils.obtainTopActivity();
        if (activity!=null)
        {
            finish(activity);
        }
    }

    public  void finish(Activity activity)
    {
        finish(activity,null);
    }

    public  void finish(Activity activity,Integer requestCode)
    {
        finish(activity,requestCode,null);
    }

    public  void finish(Activity activity,Integer requestCode,Bundle bundle)
    {
        if (requestCode!=null)
        {
            Intent intent = new Intent();
            if (bundle!=null)
            {
                intent.putExtras(bundle);
            }
            activity.setResult(requestCode,intent);
        }
        activity.finish();
        if (list!=null&&list.size()>0)
        {
            for (int i = 0; i < list.size(); i++) {
                activity.overridePendingTransition(0,list.get(i));
            }
        }

    }
}
