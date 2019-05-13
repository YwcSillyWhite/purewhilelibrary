package com.purewhite.ywc.purewhitelibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

/**
 * @author yuwenchao
 */
public class DialogUtils {

    private Dialog dialog;
    public Dialog getDialog() {
        return dialog;
    }

    private Context context;
    private View view;
    private SparseArray<View> sparseArray;
    private OnSingleListener onSingleListener;
    private Window window;

    public DialogUtils(Context context) {
      this(context,R.style.BaseDialog);
    }

    public DialogUtils(Context context,int res)
    {
        this.context=context;
        dialog = new Dialog(context,res);
        window=dialog.getWindow();
        sparseArray=new SparseArray<>();
    }

    public DialogUtils setDialogView(int layoutID)
    {
        return setDialogView(layoutID,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public DialogUtils setDialogView(int layoutID,int weight,int height)
    {
        View view = LayoutInflater.from(context).inflate(layoutID, null);
        return setDialogView(view,weight,height);
    }

    public DialogUtils setDialogView(View view)
    {
        return setDialogView(view,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public DialogUtils setDialogView(View view,int weight,int height)
    {
        this.view=view;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(weight,height);
        dialog.setContentView(view,layoutParams);
        return this;
    }

    public DialogUtils setOnClick(OnSingleListener onClick)
    {
        this.onSingleListener=onClick;
        return this;
    }


    public DialogUtils setChildText(@IdRes int id)
    {
        return setChildText(id,null);
    }


    public DialogUtils setChildText(@IdRes int id,boolean click)
    {
        return setChildText(id,null,click);
    }

    public DialogUtils setChildText(@IdRes int id,String content)
    {
        return setChildText(id,content,false);
    }


    //设置屏幕大小
    public DialogUtils setScreenWidth(float num)
    {
        if (num>0&&num<=1)
        {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width =(int)(SizeUtils.getScreenWidth()*num);
            window.setAttributes(lp);
        }
        return this;
    }


    protected void setAnim(@StyleRes int resId)
    {

        window.setWindowAnimations(resId);
    }







    public DialogUtils setChildText(@IdRes int id,String content,boolean click)
    {
        View childView = fdView(id);
        if (!TextUtils.isEmpty(content)&&childView instanceof TextView)
        {
            ((TextView) childView).setText(content);
        }
        setClick(click,childView);
        return this;
    }


    public DialogUtils setChildImg(@IdRes int id,Object object)
    {
        return setChildImg(id,object,false);
    }


    public DialogUtils setChildImg(@IdRes int id,Object object,boolean click)
    {
        View childView = fdView(id);
        if (object!=null&&childView instanceof ImageView)
        {
            ImageLoader.newInstance().init(((ImageView) childView),object);
        }
        setClick(click,childView);
        return this;
    }

    public void show()
    {
        if (dialog!=null&&!dialog.isShowing())
        {
            dialog.show();
        }
    }

    public void dismiss()
    {
        if (dialog!=null&&dialog.isShowing())
        {
            dialog.dismiss();
        }
    }

    public void onDestroy()
    {
        dismiss();
        if (dialog!=null)
        {
            dialog=null;
        }
    }


    private View fdView(@IdRes int id){
        View viewChild = sparseArray.get(id);
        if (viewChild==null)
        {
            viewChild=view.findViewById(id);
            sparseArray.put(id,viewChild);
        }
        return viewChild;
    }

    private void setClick(boolean click,View view)
    {
        if (click&&onSingleListener!=null)
        {
            view.setOnClickListener(onSingleListener);
        }
    }


}
