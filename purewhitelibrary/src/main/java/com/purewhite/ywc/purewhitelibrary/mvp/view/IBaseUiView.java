package com.purewhite.ywc.purewhitelibrary.mvp.view;

import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 * ；ui视图接口
 */

public interface IBaseUiView {

    Context getContext();

    Fragment getFragment();
}
