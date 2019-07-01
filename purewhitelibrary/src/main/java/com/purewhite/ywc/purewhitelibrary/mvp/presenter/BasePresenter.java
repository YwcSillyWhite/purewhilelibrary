package com.purewhite.ywc.purewhitelibrary.mvp.presenter;


import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 */

public interface BasePresenter<V extends BaseUiView>  {

    void addView(V view);

    void deleteView();

    //显示load
    void showLoad(String content);

    //隐藏load
    void hideLoad();
}
