package com.purewhite.ywc.purewhitelibrary.adapter.ptr;

import android.content.Context;
import android.util.AttributeSet;

import com.purewhite.ywc.purewhitelibrary.adapter.ptr.base.BasePtrFrameLayout;
import com.purewhite.ywc.purewhitelibrary.adapter.ptr.head.PtrFrameHead;


/**
 * @author yuwenchao
 */
public class PtrLayout extends BasePtrFrameLayout {

    public PtrLayout(Context context) {
        super(context);
    }

    public PtrLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PtrLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        addHeadView(new PtrFrameHead(getContext()));
    }


}
