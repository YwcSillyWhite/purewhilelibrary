package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;

import com.purewhite.ywc.purewhitelibrary.R;

/**
 * @author yuwenchao
 */
public abstract class BaseSkipActivity extends BaseActivity{


    /**
     * 跳转
     * @param cla
     */
    public void skipActivity(Class<?> cla)
    {
        skipActivity(cla, null,null);
    }

    public void skipActivity(Class<?> cla,Bundle bundle)
    {
        skipActivity(cla,bundle,null);
    }

    public void skipActivity(Class<?> cla,Integer requestCode)
    {
        skipActivity(cla,null,requestCode);
    }

    public void skipActivity(Class<?> cla,Bundle bundle,Integer requestCode)
    {
        skipActivity(new Intent(this,cla),bundle,requestCode);
    }


    public void skipActivity(Intent intent)
    {
        skipActivity(intent,null,null);
    }

    public void skipActivity(Intent intent,Integer requestCode)
    {
        skipActivity(intent,null,requestCode);
    }

    public void skipActivity(Intent intent,Bundle bundle)
    {
        skipActivity(intent,bundle,null);
    }


    /**
     * request必须大于0才调用
     * @param intent
     * @param bundle
     * @param requestCode
     */
    public void skipActivity(Intent intent,Bundle bundle,Integer requestCode)
    {
        if (bundle!=null)
        {
            intent.putExtras(bundle);
        }
        if (requestCode!=null)
        {
            super.startActivityForResult(intent,requestCode);
        }
        else
        {
            super.startActivity(intent);
        }
    }


    /**
     * 动画跳转
     * @param cla
     */
    public void skipActivityAnim(Class<?> cla)
    {
        skipActivityAnim(cla,null,null);
    }

    public void skipActivityAnim(Class<?> cla,Bundle bundle)
    {
        skipActivityAnim(cla,bundle,null);
    }

    public void skipActivityAnim(Class<?> cla,int requestCode)
    {
        skipActivityAnim(cla,null,null);
    }

    public void skipActivityAnim(Class<?> cla,Bundle bundle,Integer requestCode)
    {
        skipActivityAnim(new Intent(this,cla),bundle,requestCode);
    }


    public void skipActivityAnim(Intent intent)
    {
        skipActivityAnim(intent,null,null);
    }

    public void skipActivityAnim(Intent intent,Integer requestCode)
    {
        skipActivityAnim(intent,null,requestCode);
    }

    public void skipActivityAnim(Intent intent,Bundle bundle)
    {
        skipActivityAnim(intent,bundle,null);
    }


    /**
     * request必须大于0才调用
     * @param intent
     * @param bundle
     * @param requestCode
     */
    public void skipActivityAnim(Intent intent,Bundle bundle,Integer requestCode)
    {
        if (bundle!=null)
        {
            intent.putExtras(bundle);
        }
        if (requestCode!=null)
        {
            super.startActivityForResult(intent,requestCode);
        }
        else
        {
            super.startActivity(intent);
        }
        skipOrBackAnim(R.anim.activity_open_enter,R.anim.activity_open_exit);
    }


    public void skipOrBackAnim(@AnimRes int enter,@AnimRes int exit)
    {
        overridePendingTransition(enter,exit);

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
        if (requestCode!=null)
        {
            Intent intent = new Intent();
            if (bundle!=null)
            {
                intent.putExtras(bundle);
            }
            setResult(requestCode,intent);
        }
        finish();
    }


    //结束动画默认是关闭的
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAnim();
    }

    @Override
    public void finish() {
        super.finish();
        finishAnim();
    }

    //是否存在结束动画
    protected boolean isFinishAnim()
    {
        return false;
    }


    private void finishAnim()
    {
        if (isFinishAnim())
        {
            skipOrBackAnim(R.anim.activity_close_enter,R.anim.activity_close_exit);
        }
    }

}
