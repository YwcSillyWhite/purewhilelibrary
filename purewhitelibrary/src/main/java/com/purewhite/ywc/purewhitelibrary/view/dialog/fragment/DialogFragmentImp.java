package com.purewhite.ywc.purewhitelibrary.view.dialog.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.IdRes;

public class DialogFragmentImp extends BaseDialogFragment{

    private int layoutId;

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
    protected void initView() {

    }

    @Override
    protected void afterView() {

    }
}
