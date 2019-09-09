package com.purewhite.ywc.purewhitelibrary.mvp.presenter;


import com.purewhite.ywc.purewhitelibrary.mvp.view.IBaseUiView;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 */

public interface IBasePresenter<V extends IBaseUiView>  {

    void addView(V view);

    void deleteView();
}
