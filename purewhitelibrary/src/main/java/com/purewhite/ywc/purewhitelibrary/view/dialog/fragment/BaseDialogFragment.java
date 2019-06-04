package com.purewhite.ywc.purewhitelibrary.view.dialog.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;


/**
 * @author yuwenchao
 */
public abstract class BaseDialogFragment<T> extends DialogFragment {

    private View viewParent;
    private Window window;
    private Dialog dialog;
    private int gravity,wight,height;
    private float scaleWight,scaleHeight;
    @StyleRes
    private int dialogAnim;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (viewParent==null)
        {
            inflater.inflate(getLayoutId(),container,false);
        }
        return viewParent;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        afterView();
    }


    @LayoutRes
    protected abstract int getLayoutId();


    protected abstract int style();

    @StyleRes
    protected abstract int theme();


    protected void beforView()
    {
        setStyle(style(),theme());
    }



    protected abstract void initView();



    /**
     * 设置位置
     * @param gravity
     * @return
     */
    public T setGravity(int gravity)
    {
        if (window==null)
        {
            this.gravity=gravity;
        }
        else
        {
            window.setGravity(gravity);
        }
        return ((T) this);
    }

    /**
     * 设置宽高
     * @param wight
     * @param height
     * @return
     */
    public T setLayout(int wight,int height)
    {
        if (window==null)
        {
            this.wight=wight;
            this.height=height;
        }
        else
        {
            window.setLayout(wight,height);
        }
        return ((T) this);
    }

    /**
     * 设置宽，占全局布局的比例
     * @param scaleWight
     * @return
     */
    public T setScreenWight(float scaleWight)
    {
        if (window==null)
        {
            this.scaleWight=scaleWight;
        }
        else
        {
            if (scaleWight>0&&scaleWight<=1)
            {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = ((int) (SizeUtils.getScreenWidth() * scaleWight));
                window.setAttributes(lp);
            }
        }
        return ((T) this);
    }


    /**
     * 设置高，占全局布局的比例
     * @param scaleHeight
     * @return
     */
    public T setScreenHight(float scaleHeight)
    {
        if (window==null)
        {
            this.scaleHeight=scaleHeight;
        }
        else
        {
            if (scaleHeight>0&&scaleHeight<=1)
            {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.height = ((int) (SizeUtils.getScreenWidth() *scaleHeight));
                window.setAttributes(lp);
            }
        }
        return ((T) this);
    }


    public T addAnim(@StyleRes int dialogAnim)
    {
        if (window==null)
        {
            this.dialogAnim=dialogAnim;
        }
        else
        {
            window.setWindowAnimations(dialogAnim);
        }
        return ((T) this);
    }


    protected  void afterView()
    {
        dialog=getDialog();
        window =dialog.getWindow();
        if (gravity!=0)
        {
            window.setGravity(gravity);
        }
        if (wight!=0&&height!=0)
        {
            window.setLayout(wight,height);
        }
        if (scaleWight>0&&scaleWight<=1)
        {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = ((int) (SizeUtils.getScreenWidth() * scaleWight));
            window.setAttributes(lp);
        }
        if (scaleHeight>0&&scaleHeight<=1)
        {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.height = ((int) (SizeUtils.getScreenHeight() * scaleHeight));
            window.setAttributes(lp);
        }
        if (dialogAnim!=0)
        {
            window.setWindowAnimations(dialogAnim);
        }
    }













    public void show(@NonNull Fragment fragment,String tag)
    {
        super.show(fragment.getChildFragmentManager(),tag);
    }


    public void show(@NonNull FragmentActivity activity, String tag)
    {
        super.show(activity.getSupportFragmentManager(),tag);
    }





}
