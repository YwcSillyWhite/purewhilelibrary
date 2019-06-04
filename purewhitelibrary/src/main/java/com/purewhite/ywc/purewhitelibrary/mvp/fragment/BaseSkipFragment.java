package com.purewhite.ywc.purewhitelibrary.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.v4.app.FragmentActivity;

import com.purewhite.ywc.purewhitelibrary.R;

/**
 * @author yuwenchao
 */
public abstract class BaseSkipFragment extends BaseFragment {


    public void skipOrBackAnim(@AnimRes int enter, @AnimRes int exit)
    {
        getActivity().overridePendingTransition(enter,exit);
    }

    public void skipFragmentAnim(Class<?> cla)
    {
        skipFragmentAnim(cla,null,null);
    }

    public void skipFragmentAnim(Class<?> cla, Bundle bundle)
    {
        skipFragmentAnim(cla,bundle,null);
    }

    public void skipFragmentAnim(Class<?> cla,Integer requestCode)
    {
        skipFragmentAnim(cla,null,requestCode);
    }

    public void skipFragmentAnim(Class<?> cla,Bundle bundle,Integer requestCode)
    {
        skipFragmentAnim(new Intent(getContext(),cla),bundle,requestCode);
    }

    public void skipFragmentAnim(Intent intent)
    {
        skipFragmentAnim(intent,null,null);
    }

    public void skipFragmentAnim(Intent intent,Bundle bundle)
    {
        skipFragmentAnim(intent,bundle,null);
    }

    public void skipFragmentAnim(Intent intent, Bundle bundle, Integer requestCode)
    {

        skipFragment(intent,bundle,requestCode);
        skipOrBackAnim(R.anim.activity_open_enter,R.anim.activity_open_exit);
    }


    public void skipFragment(Class<?> cla)
    {
        skipFragment(cla,null,null);
    }

    public void skipFragment(Class<?> cla, Bundle bundle)
    {
        skipFragment(cla,bundle,null);
    }

    public void skipFragment(Class<?> cla,Integer requestCode)
    {
        skipFragment(cla,null,requestCode);
    }

    public void skipFragment(Class<?> cla,Bundle bundle,Integer requestCode)
    {
        skipFragment(new Intent(getContext(),cla),bundle,requestCode);
    }

    public void skipFragment(Intent intent)
    {
        skipFragment(intent,null,null);
    }

    public void skipFragment(Intent intent,Bundle bundle)
    {
        skipFragment(intent,bundle,null);
    }

    public void skipFragment(Intent intent, Bundle bundle, Integer requestCode)
    {

        if (bundle!=null)
        {
            intent.putExtras(bundle);
        }
        if (requestCode!=null)
        {
            startActivityForResult(intent,requestCode);
        }
        else
        {
            startActivity(intent);
        }
    }





    public void skipActivityAnim(Class<?> cla)
    {
        skipActivityAnim(cla,null,null);
    }

    public void skipActivityAnim(Class<?> cla,Bundle bundle)
    {
        skipActivityAnim(cla,bundle,null);
    }

    public void skipActivityAnim(Class<?> cla,Integer integer)
    {
        skipActivityAnim(cla,null,integer);
    }

    public void skipActivityAnim(Class<?> cla,Bundle bundle,Integer integer)
    {
        skipActivityAnim(new Intent(getContext(),cla),bundle,integer);
    }

    public void skipActivityAnim(Intent intent)
    {
        skipActivityAnim(intent,null,null);
    }

    public void skipActivityAnim(Intent intent,Bundle bundle)
    {
        skipActivityAnim(intent,bundle,null);
    }

    public void skipActivityAnim(Intent intent,Integer integer)
    {
        skipActivityAnim(intent,null,integer);
    }

    public void skipActivityAnim(Intent intent,Bundle bundle,Integer requestCode)
    {
        skipActivity(intent,bundle,requestCode);
        skipOrBackAnim(R.anim.activity_open_enter,R.anim.activity_open_exit);
    }



    public void skipActivity(Class<?> cla)
    {
        skipActivity(cla,null,null);
    }

    public void skipActiivty(Class<?> cla,Bundle bundle)
    {
        skipActivity(cla,bundle,null);
    }

    public void skipActivity(Class<?> cla,Integer integer)
    {
        skipActivity(cla,null,integer);
    }

    public void skipActivity(Class<?> cla,Bundle bundle,Integer integer)
    {
        skipActivity(new Intent(getContext(),cla),bundle,integer);
    }

    public void skipActivity(Intent intent)
    {
        skipActivity(intent,null,null);
    }

    public void skipActivity(Intent intent,Bundle bundle)
    {
        skipActivity(intent,bundle,null);
    }

    public void skipActivity(Intent intent,Integer integer)
    {
        skipActivity(intent,null,integer);
    }

    public void skipActivity(Intent intent,Bundle bundle,Integer requestCode)
    {
        if (bundle!=null)
        {
            intent.putExtras(bundle);
        }
        if (requestCode!=null)
        {
            getActivity().startActivityForResult(intent,requestCode);
        }
        else
        {
            startActivity(intent);
        }
        skipOrBackAnim(R.anim.activity_open_enter,R.anim.activity_open_exit);
    }








    /**
     * 回退activity
     */
    public void backActivity()
    {
        backActivity(null);
    }

    public void backActivity(Integer requestCode)
    {
        backActivity(null,requestCode);
    }


    public void backActivity(Bundle bundle,Integer requestCode)
    {
        FragmentActivity activity = getActivity();
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
    }

}
