package com.purewhite.ywc.purewhitelibrary.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseSkipActivity;

/**
 * @author yuwenchao
 */
public abstract class BaseSkipFragment extends BaseBarEventbusFragment {


    public void skipActivity(Class<?> cla)
    {
        skipActivity(cla,-1,null);
    }

    public void skipActivity(Class<?> cla,Bundle bundle)
    {
        skipActivity(cla,-1,bundle);
    }

    public void skipActivity(Class<?> cla,int requestCode)
    {
        skipActivity(cla,requestCode,null);
    }

    public void skipActivity(Class<?> cla,int requestCode,Bundle bundle)
    {
        skipActivity(new Intent(getActivity(),cla),requestCode,bundle);
    }

    public void skipActivity(Intent intent,int requestCode)
    {
        skipActivity(intent,requestCode,null);
    }

    public void skipActivity(Intent intent,Bundle bundle)
    {
        skipActivity(intent,-1,bundle);
    }

    public void skipActivity(Intent intent,int requestCode,Bundle bundle)
    {
        skipActivity(intent,requestCode,bundle,0,false);
    }


    public void skipActivity(Class<?> cla,int requestCode,Bundle bundle,int skipAnimStatue,boolean isActivity)
    {
        skipActivity(new Intent(getContext(),cla),requestCode,bundle,skipAnimStatue,isActivity);
    }

    /**
     * request必须大于0才调用
     * @param intent
     * @param bundle
     * @param requestCode
     */
    public void skipActivity(Intent intent,int requestCode,Bundle bundle,int skipAnimStatue,boolean isActivity)
    {
        if (bundle!=null)
        {
            intent.putExtras(bundle);
        }
        if (isActivity)
        {
            getActivity().startActivityForResult(intent,requestCode);
        }
        else
        {
            super.startActivityForResult(intent,requestCode);
        }
        if (getActivity() instanceof BaseSkipActivity)
        {
            ((BaseSkipActivity) getActivity()).skipAnim(skipAnimStatue);
        }
    }



    /**
     * 回退activity
     */
    public void finishActivity()
    {
        finishActivity(-1);
    }

    public void finishActivity(Integer requestCode)
    {
        finishActivity(requestCode,null);
    }


    public void finishActivity(Integer requestCode,Bundle bundle)
    {
        FragmentActivity activity = getActivity();
        if (requestCode!=null)
        {
            if (bundle!=null)
            {
                Intent intent = new Intent();
                if (bundle!=null)
                {
                    intent.putExtras(bundle);
                }
                activity.setResult(requestCode,intent);
            }
            else
            {
                activity.setResult(requestCode,null);
            }
        }
        activity.finish();
    }

}
