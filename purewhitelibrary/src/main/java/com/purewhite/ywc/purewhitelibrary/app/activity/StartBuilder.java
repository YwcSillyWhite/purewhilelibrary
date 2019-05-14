package com.purewhite.ywc.purewhitelibrary.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public class StartBuilder {

    private List<Integer> list;
    public StartBuilder with(@AnimRes int ex)
    {
        if (list==null)
        {
            list=new ArrayList<>();
        }
        list.add(ex);
        return this;
    }


    public  void startActivity(Activity context)
    {
        startActivity(context.getClass());
    }

    public  void startActivity(Class<?> cls)
    {
        startActivity(cls,null);
    }
    public  void startActivity(Class<?> cls, Bundle bundle)
    {
        startActivity(cls,bundle,null,0);
    }

    public void startActivity(Class<?> cls, Context context, int requestCode)
    {
        startActivity(cls,null,context,requestCode);
    }

    public  void startActivity(Class<?> cls, Bundle bundle, Context context, int requestCode)
    {
        if (cls==null) {
            return;
        }
        Intent intent=new Intent(AppUtils.getContext(),cls);
        if (bundle!=null) {
            intent.putExtras(bundle);
        }
        startActivity(intent,context,requestCode);
    }

    public  void startActivity(Intent intent)
    {
        startActivity(intent,null,0);
    }

    public  void startActivity(Intent intent, Context context,int requestCode)
    {

        if (context!=null&&context instanceof Activity)
        {
            Activity activity=(Activity) context;
            activity.startActivityForResult(intent,requestCode);

            if (list!=null&&list.size()>0)
            {
                for (int i = 0; i < list.size(); i++) {
                    activity.overridePendingTransition(list.get(i),0);
                }
            }

        }
        else
        {
            context=AppUtils.getContext();
            context.startActivity(intent);
            if (context instanceof Activity)
            {
                for (int i = 0; i < list.size(); i++) {
                    ((Activity) context).overridePendingTransition(list.get(i),0);
                }
            }
        }

    }
}
