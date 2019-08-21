package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.content.Intent;
import android.os.Bundle;

import com.purewhite.ywc.purewhitelibrary.R;

/**
 * skip默认跳转都是第一种
 * 跳转动画
 * @author yuwenchao
 */
public abstract class BaseSkipActivity extends BaseBarEventbusActivity {



    /**
     *
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

    public void skipActivity(Intent intent,Integer requestCode,Bundle bundle)
    {
        skipActivity(intent,requestCode,bundle,0);
    }

    public void skipActivity(Class<?> cla,int requestCode,Bundle bundle,int skipAnimStatue)
    {
        skipActivity(new Intent(this,cla),requestCode,bundle,skipAnimStatue);
    }

    /**
     * request必须大于等于0才调用
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
        skipAnim(skipAnimStatue);
    }


    public void skipAnim(int skipAnimStatue)
    {
        if (skipAnimStatue<0)
            return;
        switch (skipAnimStatue)
        {
            case 0:
                overridePendingTransition(R.anim.pure_enter_right_p_300,R.anim.pure_exit_left_p_300);
                break;
            case 1:
                overridePendingTransition(R.anim.pure_enter_alpha,0);
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





    private int finishAnimStatue=0;
    public void setFinishAnimStatue(int finishAnimStatue) {
        this.finishAnimStatue = finishAnimStatue;
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
        if (finishAnimStatue<0)
            return;
        //skipAnimStatueNew 负数就没有动画了
        switch (finishAnimStatue)
        {
            case 0:
                overridePendingTransition(0,R.anim.pure_exit_right_p_300);
                break;
            case 1:
                overridePendingTransition(0,R.anim.pure_exit_alpha);
                break;
        }
    }
}
