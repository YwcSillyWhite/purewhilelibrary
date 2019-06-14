package com.purewhite.ywc.purewhitelibrary.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.purewhite.ywc.purewhitelibrary.R;

/**
 * @author yuwenchao
 */
public abstract class BaseSkipFragment extends BaseFragment {


    public void skipActivity(Class<?> cla)
    {
        skipActivity(cla,false);
    }

    public void skipActivity(Class<?> cla,boolean isActivity)
    {
        skipActivity(cla,null,null,isActivity);
    }

    public void skipActivity(Class<?> cla,Bundle bundle)
    {
        skipActivity(cla,bundle,false);
    }

    public void skipActivity(Class<?> cla,Bundle bundle,boolean isActivity)
    {
        skipActivity(cla,bundle,null,isActivity);
    }

    public void skipActivity(Class<?> cla,Integer integer)
    {
        skipActivity(cla,integer,false);
    }

    public void skipActivity(Class<?> cla,Integer integer,boolean isActivity)
    {
        skipActivity(cla,null,integer,isActivity);
    }

    public void skipActivity(Class<?> cla,Bundle bundle,Integer integer)
    {
        skipActivity(cla,bundle,integer,false);
    }


    public void skipActivity(Class<?> cla,Bundle bundle,Integer integer,boolean isActivity)
    {
        skipActivity(new Intent(getContext(),cla),bundle,integer,isActivity);
    }





    public void skipActivity(Intent intent)
    {
        skipActivity(intent,false);
    }

    public void skipActivity(Intent intent,boolean isActivity)
    {
        skipActivity(intent,null,null,isActivity);
    }



    public void skipActivity(Intent intent,Bundle bundle)
    {
        skipActivity(intent,bundle,false);
    }

    public void skipActivity(Intent intent,Bundle bundle,boolean isActivity)
    {
        skipActivity(intent,bundle,null,isActivity);
    }



    public void skipActivity(Intent intent,Integer integer)
    {
        skipActivity(intent,integer,false);
    }

    public void skipActivity(Intent intent,Integer integer,boolean isActivity)
    {
        skipActivity(intent,null,integer,false);
    }



    public void skipActivity(Intent intent,Bundle bundle,Integer requestCode)
    {
        skipActivity(intent,bundle,requestCode,false);
    }


    public void skipActivity(Intent intent,Bundle bundle,Integer requestCode,boolean isActivity)
    {
        if (bundle!=null)
        {
            intent.putExtras(bundle);
        }
        if (requestCode!=null)
        {
            if (isActivity)
            {
                getActivity().startActivityForResult(intent,requestCode);
            }
            else
            {
                startActivityForResult(intent,requestCode);
            }

        }
        else
        {
            startActivity(intent);
        }
        skipAnim();
    }




    protected int skipAnimEnter()
    {
        return R.anim.pure_activity_enter_right;
    }

    protected int skipAnimExit()
    {
        return R.anim.pure_activity_exit_left;
    }


    private void skipAnim()
    {
        int skipAnimEnter = skipAnimEnter();
        int skipAnimExit = skipAnimExit();
        if (skipAnimEnter!=0||skipAnimExit!=0)
        {
            getActivity().overridePendingTransition(skipAnimEnter,skipAnimExit);
        }
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
