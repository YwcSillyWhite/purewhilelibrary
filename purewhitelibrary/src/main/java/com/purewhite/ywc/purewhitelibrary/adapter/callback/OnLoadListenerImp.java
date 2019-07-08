package com.purewhite.ywc.purewhitelibrary.adapter.callback;

/**
 * @author yuwenchao
 */
public abstract   class OnLoadListenerImp implements OnLoadListener{

    //是否可以加载更多
    protected boolean judgeLoad()
    {
        return true;
    }

    //加载更多
    public abstract void pullUp();

    @Override
    public void loadMore() {
        if (judgeLoad())
        {
            pullUp();
        }
    }

}
