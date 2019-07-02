package com.purewhite.ywc.purewhitelibrary.mvp.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

/**
 *绑定acticity
 * @author yuwenchao
 */
public abstract class BaseBindActivity<D extends ViewDataBinding> extends BaseTransitionActivity {

    protected D mDataBinding;

    @Override
    protected final void initSetView(int layoutId) {
        mDataBinding=DataBindingUtil.setContentView(this,layoutId);
    }
}
