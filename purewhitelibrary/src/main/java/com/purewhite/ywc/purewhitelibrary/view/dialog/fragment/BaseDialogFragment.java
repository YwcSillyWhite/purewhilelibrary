package com.purewhite.ywc.purewhitelibrary.view.dialog.fragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;


/**
 * @author yuwenchao
 */
public abstract class BaseDialogFragment<T> extends DialogFragment {

    private View viewParent;
    private SparseArray<View> sparseArray;
    private View.OnClickListener onClickListener;
    private Window window;


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
        sparseArray=new SparseArray<>();
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


    protected void beforView()
    {
        setStyle(style(),theme());
    }

    protected abstract int style();

    @StyleRes
    protected abstract int theme();

    protected abstract void initView();

    protected abstract int obtainGravity();

    protected abstract int screenWidth();

    protected  void afterView()
    {
        window = getDialog().getWindow();
        final int gravity = obtainGravity();
        if (gravity!=0) {
            window.setGravity(gravity);
        }
        final int screenWidth = screenWidth();
        if (screenWidth>0)
        {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width =screenWidth;
            window.setAttributes(lp);
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




    /**  viewIo **/
    public <T extends View> T fdId(@IdRes int id)
    {
        View view = sparseArray.get(id);
        if(view==null)
        {
            view = viewParent.findViewById(id);
            sparseArray.put(id,view);
        }
        return (T)view;
    }

    public boolean viewSelected(@IdRes int id) {
        return fdId(id).isSelected();
    }





    public T setView(@IdRes int id, boolean click) {
        View view = fdId(id);
        if (click&&onClickListener!=null)
        {
            view.setOnClickListener(onClickListener);
        }
        return ((T) this);
    }


    public T setSelecte(@IdRes int id,boolean selecte) {
        fdId(id).setSelected(selecte);
        return ((T) this);
    }


    public T setTextView(@IdRes int id, String context, boolean click) {
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
        return ((T) this);
    }


    public T setButton(@IdRes int id, String context, boolean click) {
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


    public T setRecycler(@IdRes int id, RecyclerView.Adapter adapter
            , RecyclerView.LayoutManager layoutManager) {
        View view = fdId(id);
        if (view instanceof RecyclerView)
        {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }
        return ((T) this);
    }

}
