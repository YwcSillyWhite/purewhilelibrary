package com.purewhite.ywc.purewhitelibrary.mvp.presenter;


import com.purewhite.ywc.purewhitelibrary.mvp.view.IBaseView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 * Presenter实现类
 */

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V>,InvocationHandler{

    private V mView;
    private WeakReference<V> weakReference;

    @Override
    public void addView(V view) {
        mView=createV(view);
    }


    private V createV(V view){
        weakReference = new WeakReference<>(view);
        try {
            return (V)Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), this);

        }catch (Exception e) {

        }
        return weakReference.get();
    }

    /**
     *  动态代理接口，每次调用了代理对象的方法最终也会回到到这里
     *
     * {@link InvocationHandler}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (weakReference == null || weakReference.get() == null||weakReference.get()==null) {
            return null;
        }
        return method.invoke(weakReference.get(), objects);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteView() {
        if (weakReference!=null)
        {
            weakReference.clear();
            mView=null;
        }
    }

    public V getView() {
        return mView;
    }



    //当前页数
    protected int page=1;
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
