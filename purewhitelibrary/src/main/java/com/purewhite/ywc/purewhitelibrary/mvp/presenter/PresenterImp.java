package com.purewhite.ywc.purewhitelibrary.mvp.presenter;


import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;
import com.purewhite.ywc.purewhitelibrary.window.dialog.utils.DialogUtils;
import com.purewhite.ywc.purewhitelibrary.window.utils.WindowPureUtils;

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

public class PresenterImp<V extends BaseUiView> implements BasePresenter<V> {

    private V mView;
    private WeakReference<V> weakReference;
    private DialogUtils dialogUtils;

    @Override
    public void addView(V view) {
        weakReference = new WeakReference<>(view);
        //动态代理
        mView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (weakReference == null || weakReference.get() == null) {
                    return null;
                }
                return method.invoke(weakReference.get(), objects);
            }
        });
    }

    @Override
    public void showLoad(String content) {
        if (dialogUtils == null)
        {
            dialogUtils=DialogUtils.builder()
                    .setContentView(R.layout.pure_dialog_load)
                    .setCanceled(false)
                    .setCanceledOnTouchOutside(false)
                    .buildDialog(mView.getContext())
                    .setSplace(0.8f,0);
        }
        dialogUtils.setTextView(R.id.dialog_content,content,false);
        dialogUtils.show();
    }

    @Override
    public void hideLoad() {
        if (dialogUtils!=null)
        {
            dialogUtils.dismiss();
        }
    }

    @Override
    public void deleteView() {
        //销毁dialog
        WindowPureUtils.onDialogDestory(dialogUtils);
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
