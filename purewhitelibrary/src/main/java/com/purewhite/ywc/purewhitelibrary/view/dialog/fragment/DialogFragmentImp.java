package com.purewhite.ywc.purewhitelibrary.view.dialog.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;

import com.purewhite.ywc.purewhitelibrary.R;

public class DialogFragmentImp extends BaseDialogFragment{

    private int layoutId;
    private float scale;
    private int gravity;

    public static DialogFragmentImp newInstance(@IdRes int layoutId) {
        DialogFragmentImp fragment = new DialogFragmentImp();
        Bundle bundle = new Bundle();
        bundle.putInt("layoutId",layoutId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected void beforView() {
        layoutId=getArguments().getInt("layoutId",0);
    }

    @Override
    protected int style() {
        return R.style.BaseDialog;
    }

    @Override
    protected int theme() {
        return DialogFragment.STYLE_NO_TITLE;
    }



    @Override
    protected void initView() {

    }


}
