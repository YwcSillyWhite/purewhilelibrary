package com.purewhite.ywc.purewhitelibrary.window.dialog.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

/**
 * @author yuwenchao
 */
public abstract class WindowDialogUtils<T extends WindowDialogUtils>{

    protected View.OnClickListener onClickListener;
    protected View viewParent;
    protected Dialog dialog;
    protected Window window;
    protected Context context;
    private SparseArray<View> sparseArray=new SparseArray<>();

    public T setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return ((T) this);
    }

    public <V extends View> V fdId(@IdRes int id) {
        View view = sparseArray.get(id);
        if (id!=0)
        {
            if (view==null)
            {
                view=viewParent.findViewById(id);
                sparseArray.put(id,view);
            }
        }
        return ((V) view);
    }

    public boolean isSelected(@IdRes int id) {
        return fdId(id).isSelected();
    }

    public T setSelecte(@IdRes int id, boolean selecte) {
        fdId(id).setSelected(selecte);
        return ((T) this);
    }

    public T setClick(@IdRes int ...ids)
    {
        if (onClickListener!=null)
        {
            for(int id:ids)
            {
                fdId(id).setOnClickListener(onClickListener);
            }
        }
       return ((T) this);
    }

    public T setTextView(@IdRes int id, String context, boolean click) {
        View view = fdId(id);
        if (view instanceof TextView)
        {
            TextView textView = (TextView) view;
            textView.setText(TextUtils.isEmpty(context)?"":context);
            if (click&&onClickListener!=null) {
                textView.setOnClickListener(onClickListener);
            }
        }
        return ((T) this);
    }

    public T setButton(@IdRes int id, String context, boolean click) {
        View view = fdId(id);
        if (view instanceof Button)
        {
            Button button = (Button) view;
            button.setText(TextUtils.isEmpty(context)?"":context);
            if (click&&onClickListener!=null) {
                button.setOnClickListener(onClickListener);
            }
        }
        return ((T) this);
    }

    public T setImageView(@IdRes int id, Object object, boolean click) {
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
        return ((T) this);
    }


    public T setRecycler(@IdRes int id, RecyclerView.Adapter adapter,
                                   RecyclerView.LayoutManager layoutManager) {
        View view = fdId(id);
        if (view instanceof RecyclerView)
        {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }
        return ((T) this);
    }


    /**
     * setCancelable 默认是true ，是点击返回dialog销毁，false就是不销毁
     * @return
     */
    public T setCancelable(boolean cancelable)
    {
        dialog.setCancelable(cancelable);
        return ((T) this);
    }


    /**
     * 绑定activity
     * @param activity
     * @return
     */
    public T bindActivity(@NonNull final Activity activity)
    {
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // 关闭 Dialog
                    dialog.dismiss();
                    // 关闭当前 Activity
                    activity.finish();
                    // 返回 true，表示返回事件已被处理，不再向下传递
                    return true;
                } else {
                    return false;
                }
            }
        });
        return ((T) this);
    }



    /**
     *  //添加动画
     * @param resId
     * @return
     */
    public T addAnim(@StyleRes int resId) {
        window.setWindowAnimations(resId);
        return ((T) this);
    }


    /**
     * 显示
     */
    public void show()
    {
        if (!dialog.isShowing())
        {
            dialog.show();
        }
    }


    /**
     * 隐藏
     */
    public void dismiss()
    {
        if (dialog.isShowing())
        {
            dialog.dismiss();
        }
    }


    /**
     * 隐藏
     */
    public void onDestroy()
    {
        dismiss();
    }

}
