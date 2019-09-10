package com.purewhite.ywc.purewhitelibrary.mvp.presenter;


import com.purewhite.ywc.purewhitelibrary.mvp.view.IBasePureView;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 */

public interface IBasePurePresenter<V extends IBasePureView>  {

    void addView(V view);

    void deleteView();
}
