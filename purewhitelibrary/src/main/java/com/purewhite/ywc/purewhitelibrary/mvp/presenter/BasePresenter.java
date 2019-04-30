package com.purewhite.ywc.purewhitelibrary.mvp.presenter;


import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseView;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 */

public interface BasePresenter<V extends BaseView>  {

    void addView(V view);

    void deleteView();
}
