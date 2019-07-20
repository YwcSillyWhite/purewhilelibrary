package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.Intent;
import android.os.Bundle;

import com.purewhite.ywc.purewhitelibrary.R;

/**
 * 只是作为参考
 * 跳转动画
 * @author yuwenchao
 */
public abstract class BaseSkipActivity extends BaseBarActivity{

    private int finishAnimStatue=1;
    public void setFinishAnimStatue(int finishAnimStatue) {
        this.finishAnimStatue = finishAnimStatue;
    }

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
        skipActivity(cla,requestCode,bundle,1);
    }

    public void skipActivity(Class<?> cla,int requestCode,Bundle bundle,int skipAnimStatue)
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
        skipActivity(intent,requestCode,bundle,1);
    }



    /**
     * request必须大于0才调用
     * @param intent
     * @param bundle
     * @param requestCode
     */
    public void skipActivity(Intent intent,Integer requestCode,Bundle bundle,int skipAnimStatue)
    {
        if (bundle!=null)
        {
            intent.putExtras(bundle);
        }
        super.startActivityForResult(intent,requestCode);
        switch (skipAnimStatue)
        {
            case 1:
                overridePendingTransition(R.anim.pure_activity_enter_right,R.anim.pure_activity_exit_left);
                break;
            case 2:
                overridePendingTransition(R.anim.pure_actiivty_enter_alpha,0);
                break;

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

    private void finishAnim()
    {
        //skipAnimStatueNew 负数就没有动画了
        switch (finishAnimStatue)
        {
            case 1:
                overridePendingTransition(0,R.anim.pure_activity_exit_right);
                break;
            case 2:
                overridePendingTransition(0,R.anim.pure_activity_exit_alpha);
                break;
        }
    }
}
