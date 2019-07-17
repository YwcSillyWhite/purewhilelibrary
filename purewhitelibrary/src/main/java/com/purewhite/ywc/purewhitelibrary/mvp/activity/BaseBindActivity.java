package com.purewhite.ywc.purewhitelibrary.mvp.activity;


import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 *绑定acticity
 * @author yuwenchao
 */
public abstract class BaseBindActivity<D extends ViewDataBinding> extends BaseSkipActivity {

    protected D mDataBinding;

    @Override
    protected final void initSetView(int layoutId) {
        mDataBinding= DataBindingUtil.setContentView(this,layoutId);
    }
}
