package com.purewhite.ywc.purewhitelibrary.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.StyleRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.app.activity.ActivityUtils;
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

    public static final class DialogStyle
    {
        public static final int left_anim=R.style.LeftAnimStyle;
        public static final int right_anim=R.style.RightAnimStyle;
        public static final int top_anim=R.style.TopAnimStyle;
        public static final int bottom_anim=R.style.BottomAnimStyle;
    }

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





    public DialogUtils setChildRecycler(@IdRes int id, RecyclerView.Adapter adapter
            ,RecyclerView.LayoutManager layout)
    {
        View childView = fdChildView(id);
        if (childView!=null&&childView instanceof RecyclerView)
        {
            RecyclerView recyclerView = (RecyclerView) childView;
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layout);
        }
        return this;
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


    //位置
    public DialogUtils setGravity(int gravity)
    {
        window.setGravity(gravity);
        return this;
    }

    //动画
    public DialogUtils setAnim(@StyleRes int resId)
    {

        window.setWindowAnimations(resId);
        return this;
    }

    /**
     * setCancelable 默认是true ，是点击返回dialog销毁，false就是不销毁
     * @param flag
     * @return
     */
    public DialogUtils setCancelable(boolean flag)
    {

        dialog.setCancelable(flag);
        return this;
    }

    /**
     *
     */
    public DialogUtils setAllFinish(final Activity context)
    {
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // 关闭 Dialog
                    dialog.dismiss();
                    // 关闭当前 Activity
                    ActivityUtils.finish().finish(context);
                    // 返回 true，表示返回事件已被处理，不再向下传递
                    return true;
                } else {
                    return false;
                }
            }
        });
        return this;
    }



    public DialogUtils setChildText(@IdRes int id,String content,boolean click)
    {
        View childView = fdChildView(id);
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
        View childView = fdChildView(id);
        if (object!=null&&childView!=null&&childView instanceof ImageView)
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

    /**
     * 防止内存泄露
     */
    public void onDestroy()
    {
        dismiss();
        if (dialog!=null)
        {
            dialog=null;
        }
    }


    public <V extends View>V fdChildView(@IdRes int id){
        View viewChild = sparseArray.get(id);
        if (viewChild==null)
        {
            if (view!=null)
            {
                viewChild=view.findViewById(id);
                sparseArray.put(id,viewChild);
            }
        }
        return (V) viewChild;
    }

    private void setClick(boolean click,View view)
    {
        if (click&&onSingleListener!=null)
        {
            view.setOnClickListener(onSingleListener);
        }
    }


}
