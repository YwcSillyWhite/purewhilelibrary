package com.purewhite.ywc.purewhitelibrary.config.click;

import android.view.View;

/**
 *
 *点击事件优化，防止多次点击
 * @author yuwenchao
 * @date 2018/11/6
 */
public abstract class OnSingleListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        if (ClickUtils.clickable(v))
            onSingleClick(v);
    }

    public abstract void onSingleClick(View v);
}
