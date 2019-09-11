package com.purewhite.ywc.purewhitelibrary.mvp.presenter;


import com.purewhite.ywc.purewhitelibrary.mvp.view.IBaseView;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 */

public interface IBasePresenter<V extends IBaseView>  {

    void addView(V view);

    void deleteView();
}
