package com.purewhite.ywc.purewhitelibrary.view.dialog.base;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

/**
 * @author yuwenchao
 */
public abstract class WindowUtils<T extends WindowUtils>{

    private SparseArray<View> sparseArray=new SparseArray<>();
    protected View viewParent;
    private View.OnClickListener onClickListener;
    public T setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return ((T) this);
    }


    public <V extends View> V fdId(@IdRes int id) {
        View view = sparseArray.get(id);
        if (view==null)
        {
            view=viewParent.findViewById(id);
            sparseArray.put(id,view);
        }
        return ((V) view);
    }

    public boolean isSelected(int id) {
        return fdId(id).isSelected();
    }

    public T setSelecte(int id, boolean selecte) {
        fdId(id).setSelected(selecte);
        return ((T) this);
    }

    public T setView(int id, boolean click) {
        if (click&&onClickListener!=null)
        {
            fdId(id).setOnClickListener(onClickListener);
        }
        return ((T) this);
    }

    public T setTextView(int id, String context, boolean click) {
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

    public T setButton(int id, String context, boolean click) {
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

    public T setImageView(int id, Object object, boolean click) {
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


    public T setRecycler(int id, RecyclerView.Adapter adapter,
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





    //显示
    public abstract void show();


    //yinc
    public abstract void dismiss();


    //销毁
    public abstract void onDestroy();


    //添加布局
    public abstract T addLayout(@LayoutRes int id);

    public abstract T addLayout(@LayoutRes int id, ViewGroup.LayoutParams layoutParams);


    //添加动画
    public abstract T addAnim(@StyleRes int resId);


    /**
     * setCancelable 默认是true ，是点击返回dialog销毁，false就是不销毁
     * @return
     */
    public abstract T setCancelable(boolean cancelable);


    //绑定activity
    public abstract T bindActivity(final Activity activity);

}
