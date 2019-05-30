package com.purewhite.ywc.purewhitelibrary.view.dialog.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.app.activity.ActivityRollbackUtils;
import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;


/**
 * @author yuwenchao
 */
public class DialogUtils {

    private Dialog dialog;
    private Context context;
    private View viewParent;
    private SparseArray<View> sparseArray;
    private View.OnClickListener onClickListener;
    private Window window;
    public DialogUtils setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }
    public Dialog getDialog() {
        return dialog;
    }

    public DialogUtils(Context context,@LayoutRes int layoutId) {
      this(context,layoutId,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    public DialogUtils(Context context,@LayoutRes int layoutId,ViewGroup.LayoutParams layoutParams)
    {
        this(context,layoutId,layoutParams,R.style.BaseDialog);
    }


    public DialogUtils(Context context,@LayoutRes int layoutId,ViewGroup.LayoutParams layoutParams,@StyleRes int res)
    {
        this.context=context;
        sparseArray=new SparseArray<>();
        viewParent = LayoutInflater.from(context).inflate(layoutId, null);
        dialog = new Dialog(context,res);
        window=dialog.getWindow();
        dialog.setContentView(viewParent,layoutParams);
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
                    ActivityRollbackUtils.finish(context);
                    // 返回 true，表示返回事件已被处理，不再向下传递
                    return true;
                } else {
                    return false;
                }
            }
        });
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
        if (dialog!=null)
        {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog=null;
        }
    }











    public <T extends View> T fdId(@IdRes int id) {
        View view = sparseArray.get(id);
        if (view==null)
        {
            view=viewParent.findViewById(id);
            sparseArray.put(id,view);
        }
        return ((T) view);
    }

    public boolean viewSelected(int id) {
        return fdId(id).isSelected();
    }






    public DialogUtils setView(int id, boolean click) {
        if (click&&onClickListener!=null)
        {
            fdId(id).setOnClickListener(onClickListener);
        }
        return this;
    }




    public DialogUtils setSelecte(int id, boolean selecte) {
        fdId(id).setSelected(selecte);
        return this;
    }


    public DialogUtils setTextView(int id, String context, boolean click) {
        View view = fdId(id);
        if (view instanceof TextView)
        {
            TextView textView = (TextView) view;
            if (!TextUtils.isEmpty(context)) {
                textView.setText(context);
            }
            if (click&&onClickListener!=null) {
                textView.setOnClickListener(onClickListener);
            }
        }
        return this;
    }


    public DialogUtils setButton(int id, String context, boolean click) {
        View view = fdId(id);
        if (view instanceof Button)
        {
            Button button = (Button) view;
            if(!TextUtils.isEmpty(context)) {
                button.setText(context);
            }
            if (click&&onClickListener!=null) {
                button.setOnClickListener(onClickListener);
            }
        }
        return this;

    }



    public DialogUtils setImageView(int id, Object object, boolean click) {
        View view = fdId(id);
        if (view instanceof ImageView)
        {
            ImageView imageView = (ImageView) view;
            if (object!=null) {
                ImageLoader.newInstance().init(imageView,object);
            }
            if (click&&onClickListener!=null) {
                imageView.setOnClickListener(onClickListener);
            }
        }
        return this;
    }


    public DialogUtils setRecycler(int id, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
        View view = fdId(id);
        if (view instanceof RecyclerView)
        {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }
        return this;
    }
}
