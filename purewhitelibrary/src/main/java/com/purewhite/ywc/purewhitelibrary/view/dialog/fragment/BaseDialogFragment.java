package com.purewhite.ywc.purewhitelibrary.view.dialog.fragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;


/**
 * @author yuwenchao
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private View viewParent;
    private SparseArray<View> sparseArray;
    private View.OnClickListener onClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        beforView();
        if (viewParent==null)
        {
            inflater.inflate(getLayoutId(),container,false);
        }
        sparseArray=new SparseArray<>();
        return viewParent;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        afterView();
    }

    @LayoutRes
    protected abstract int getLayoutId();


    protected abstract void beforView();

    protected abstract void initView();

    protected abstract void afterView();



    public void show(@NonNull Fragment fragment,String tag)
    {
        super.show(fragment.getChildFragmentManager(),tag);
    }


    public void show(@NonNull FragmentActivity activity, String tag)
    {
        super.show(activity.getSupportFragmentManager(),tag);
    }




    /**  viewIo **/
    public <T extends View> T fdId(@IdRes int id)
    {
        View view = sparseArray.get(id);
        if(view==null)
        {
            view = viewParent.findViewById(id);
            sparseArray.put(id,viewParent);
        }
        return (T)view;
    }



    public void setView(@IdRes int id, boolean click) {
        View view = fdId(id);
        if (click&&onClickListener!=null)
        {
            view.setOnClickListener(onClickListener);
        }
    }


    public boolean viewSelected(@IdRes int id) {
        return fdId(id).isSelected();
    }


    public void setSelecte(@IdRes int id,boolean selecte) {
        fdId(id).setSelected(selecte);
    }


    public void setTextView(@IdRes int id, String context, boolean click) {
        View view = fdId(id);
        if (view instanceof TextView)
        {
            TextView textView = (TextView) view;
            if(!TextUtils.isEmpty(context)) {
                textView.setText(context);
            }
            if (click&&onClickListener!=null) {
                textView.setOnClickListener(onClickListener);
            }
        }
    }


    public void setButton(@IdRes int id, String context, boolean click) {
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
    }



    public void setImageView(@IdRes int id, Object object, boolean click) {
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
    }


    public void setRecycler(@IdRes int id, RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager) {
        View view = fdId(id);
        if (view instanceof RecyclerView)
        {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }
    }

}
