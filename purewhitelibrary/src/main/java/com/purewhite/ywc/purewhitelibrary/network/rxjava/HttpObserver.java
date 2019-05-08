package com.purewhite.ywc.purewhitelibrary.network.rxjava;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author yuwenchao
 */
public abstract class HttpObserver<T> implements Observer<T> {

    private Object object;

    public HttpObserver() {
        this(AppUtils.getContext());
    }

    public HttpObserver(Object object) {
        this.object = object;
    }

    @Override
    public void onSubscribe(Disposable d) {
        RxDisposableManager.getInstance().addDis(object,d);
    }

    @Override
    public void onNext(T t) {
        if (t!=null)
        {
            onSuccess(t);
        }
        else
        {
            onFail(new Error("data is null"));
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException)
        {
            onFail(e);
        }
        else if (e instanceof ConnectException)
        {
            onFail(e);
        }
        else if (e instanceof HttpException)
        {
            onFail(e);
        }
        else
        {
            onFail(e);
        }
    }

    @Override
    public void onComplete() {
        onAfter();
    }

    protected abstract void onSuccess(T t);

    protected  void onFail(Throwable throwable)
    {
        LogUtils.error(throwable.getMessage());
        onAfter();
    }

    //执行方法之后
    protected void onAfter()
    {

    }

}
