package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;

import com.purewhite.ywc.purewhitelibrary.R;

/**
 * 只是作为参考
 * 跳转动画
 * @author yuwenchao
 */
public abstract class BaseSkipActivity extends BaseActivity{


    /**
     * 跳转
     * @param cla
     */
    public void skipActivity(Class<?> cla)
    {
        skipActivity(cla, -1,null);
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
        skipActivity(new Intent(this,cla),requestCode,bundle);
    }


    public void skipActivity(Intent intent)
    {
        skipActivity(intent,null,null);
    }

    public void skipActivity(Intent intent,Integer requestCode)
    {
        skipActivity(intent,-1,null);
    }

    public void skipActivity(Intent intent,Bundle bundle)
    {
        skipActivity(intent,-1,bundle);
    }


    /**
     * request必须大于0才调用
     * @param intent
     * @param bundle
     * @param requestCode
     */
    public void skipActivity(Intent intent,Integer requestCode,Bundle bundle)
    {
        if (bundle!=null)
        {
            intent.putExtras(bundle);
        }
        super.startActivityForResult(intent,requestCode);
        if (isSkipAnim())
        {
            //跳转动画
            int skipAnimEnter = skipAnimEnter();
            int skipAnimExit = skipAnimExit();
            if (skipAnimEnter!=0||skipAnimExit!=0)
            {
                overridePendingTransition(skipAnimEnter,skipAnimExit);
            }
        }

    }


    @AnimRes
    protected int skipAnimEnter()
    {
        return R.anim.pure_activity_enter_right;
    }

    @AnimRes
    protected int skipAnimExit()
    {
        return R.anim.pure_activity_exit_left;
    }


    protected boolean isSkipAnim()
    {
        return true;
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
        if (requestCode>=0)
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


    protected int finishAnimEnter()
    {
        return 0;
    }

    protected int finishAnimExit()
    {
        return R.anim.pure_activity_exit_right;
    }


    protected void finishAnim()
    {
        if (isFinishAnim())
        {
            int finishAnimEnter = finishAnimEnter();
            int finishAnimExit = finishAnimExit();
            if (finishAnimEnter!=0||finishAnimExit!=0)
            {
                overridePendingTransition(finishAnimEnter,finishAnimExit);
            }
        }

    }


    protected boolean isFinishAnim()
    {
        return true;
    }


}
