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
        skipActivity(cla,-1,null,true);
    }

    public void skipActivity(Class<?> cla,Bundle bundle)
    {
        skipActivity(cla,-1,bundle,true);
    }

    public void skipActivity(Class<?> cla,int requestCode,boolean isActivity)
    {
        skipActivity(cla,requestCode,null,isActivity);
    }

    public void skipActivity(Class<?> cla,int requestCode,Bundle bundle,boolean isActivity)
    {
        skipActivity(new Intent(getContext(),cla),bundle,requestCode,isActivity);
    }

    public void skipActivity(Intent intent,Bundle bundle,int requestCode,boolean isActivity)
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
            startActivityForResult(intent,requestCode);
        }
        skipAnim();
    }


    /**
     * request必须大于0才调用
     * @param intent
     * @param bundle
     * @param requestCode
     */
    public void skipActivity(Intent intent,Integer requestCode,Bundle bundle,boolean isActivity,int skipAnimStatue)
    {
        if (bundle!=null)
        {
            intent.putExtras(bundle);
        }
        super.startActivityForResult(intent,requestCode);
        switch (skipAnimStatue)
        {
            case 1:
                getActivity().overridePendingTransition(R.anim.pure_activity_enter_right,R.anim.pure_activity_exit_left);
                break;
            case 2:
                getActivity().overridePendingTransition(R.anim.pure_actiivty_enter_alpha,0);
                break;

        }
    }


    protected int skipAnimEnter()
    {
        return R.anim.pure_activity_enter_right;
    }

    protected int skipAnimExit()
    {
        return R.anim.pure_activity_exit_left;
    }

    protected boolean isSkipAnim()
    {
        return true;
    }


    private void skipAnim()
    {
        if (isSkipAnim())
        {
            getActivity().overridePendingTransition(skipAnimEnter(),skipAnimExit());
        }

    }



    /**
     * 回退activity
     */
    public void backActivity()
    {
        backActivity(-1);
    }

    public void backActivity(int requestCode)
    {
        backActivity(requestCode,null);
    }


    public void backActivity(int requestCode,Bundle bundle)
    {
        FragmentActivity activity = getActivity();
        if (requestCode>=0)
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
