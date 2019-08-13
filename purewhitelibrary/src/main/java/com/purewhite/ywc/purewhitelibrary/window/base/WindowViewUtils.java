package com.purewhite.ywc.purewhitelibrary.window.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @author yuwenchao
 */
public class WindowViewUtils<T extends WindowViewUtils>{

    private SparseArray<View> sparseArray=new SparseArray<>();
    protected View viewParent;
    protected Context context;
    protected View.OnClickListener onClickListener;


    public WindowViewUtils(View view, View.OnClickListener onClickListener)
    {
        this.viewParent=view;
        this.onClickListener=onClickListener;
    }



    public <V extends View> V fdView(@IdRes int id)
    {
        View view = sparseArray.get(id);
        if (view==null)
        {
            view=viewParent.findViewById(id);
            sparseArray.put(id,view);
        }
        return (V)view;
    }


    /**
     * 设置点击事件
     * @param
     * @return
     */
    public T setClick(@IdRes int ...ids)
    {
        if (onClickListener!=null)
        {
            for(int id:ids)
            {
                fdView(id).setOnClickListener(onClickListener);
            }
        }
        return ((T) this);
    }



    /**
     * TextView
     * @param id
     * @param content
     * @param click
     * @return
     */
    public T setTextView(@IdRes int id,String content,boolean click)
    {
        View view = fdView(id);
        if (view instanceof TextView)
        {
            TextView textView = (TextView) view;
            textView.setText(TextUtils.isEmpty(content)?"":content);
            if (click&&onClickListener!=null)
            {
                textView.setOnClickListener(onClickListener);
            }
        }
        return ((T) this);
    }

    public T setTextView(@IdRes int id, @StringRes int content, boolean click)
    {
        return setTextView(id,context.getResources().getString(content),click);
    }






    /**
     * 设置editview
     * @param id
     * @param content
     * @param hint
     * @return
     */
    public T setEditView(@IdRes int id,String content,boolean hint)
    {
        View view = fdView(id);
        if (view instanceof EditText)
        {
            EditText editText = (EditText) view;
            if (hint)
            {
                editText.setText("");
                editText.setHint(TextUtils.isEmpty(content)?"":content);
            }
            else
            {
                editText.setText(TextUtils.isEmpty(content)?"":content);
            }
        }
        return ((T) this);
    }


    public T setEditView(@IdRes int id,@StringRes int content,boolean hint)
    {
        return setEditView(id,context.getResources().getString(content),hint);
    }








    /**
     * recyclerview
     * @param id
     * @param adapter
     * @param layoutManager
     * @return
     */
    public T setRecyclerView(@IdRes int id, @NonNull RecyclerView.Adapter adapter
            ,@NonNull RecyclerView.LayoutManager layoutManager)
    {
        View view = fdView(id);
        if (view instanceof RecyclerView)
        {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }
        return ((T) this);
    }




    /**
     * button
     * @param id
     * @param content
     * @param click
     * @return
     */
    public T setButton(@IdRes int id,String content,boolean click)
    {
        View view = fdView(id);
        if (view instanceof Button)
        {
            Button button = (Button) view;
            button.setText(TextUtils.isEmpty(content)?"":content);
            if (click&&onClickListener!=null)
            {
                button.setOnClickListener(onClickListener);
            }
        }
        return ((T) this);
    }


    public T setButton(@IdRes int id,@StringRes int content,boolean click)
    {
        return setButton(id,context.getResources().getString(content),click);
    }


    /**
     * RadioButton
     * @param id
     * @param content
     * @return
     */
    public T setRadioButton(@IdRes int id,String content)
    {
        View view = fdView(id);
        if (view instanceof RadioButton)
        {
            RadioButton radioButton = (RadioButton) view;
            radioButton.setText(TextUtils.isEmpty(content)?"":content);
        }
        return ((T) this);
    }

    public T setRadioButton(@IdRes int id,@StringRes int content)
    {
        return setRadioButton(id,context.getResources().getString(content));
    }



}
