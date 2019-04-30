package com.purewhite.ywc.purewhitelibrary.mvp.presenter;



import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseView;

import java.lang.ref.WeakReference;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 * Presenter实现类
 */

public class PresenterImp<V extends BaseView> implements BasePresenter<V> {

    protected V mView;
    private WeakReference<V> vWeakReference;

    @Override
    public void addView(V view) {
        vWeakReference = new WeakReference<>(view);
        mView=vWeakReference.get();
        initPage();
    }

    @Override
    public void deleteView() {
        if (vWeakReference!=null)
        {
            vWeakReference.clear();
            mView=null;
        }
    }

    //当前页数
    private int page;
    public int getPage() {
        return page;
    }

    //初始化页数
    public void initPage()
    {
        page=1;
    }

    //设置页数
    public void setPage(int count)
    {
        page=count;
    }

    //自增
    public void  autoPage()
    {
        page++;
    }
}
