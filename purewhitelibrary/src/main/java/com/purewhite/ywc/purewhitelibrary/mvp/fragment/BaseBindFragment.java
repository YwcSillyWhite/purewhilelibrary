package com.purewhite.ywc.purewhitelibrary.mvp.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author yuwenchao
 */
public abstract class BaseBindFragment<D extends ViewDataBinding> extends BaseSkipFragment{

    protected D mDataBinding;

    @Override
    protected View setLayoutView(LayoutInflater inflater, @Nullable ViewGroup container, int layout) {
        if (mDataBinding==null) {
            mDataBinding = DataBindingUtil.inflate(inflater, layout, container, false);
        }
        return mDataBinding.getRoot();
    }
}
