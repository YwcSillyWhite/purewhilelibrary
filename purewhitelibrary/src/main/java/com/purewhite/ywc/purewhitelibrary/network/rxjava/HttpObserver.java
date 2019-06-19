package com.purewhite.ywc.purewhitelibrary.network.rxjava;

import android.content.Context;
import android.text.TextUtils;

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

    private Context context;


    /**
     * 这个生命周期app绑定的，使用的时候要注意
     */
    public HttpObserver()
    {
        this(AppUtils.getContext());
    }

    public HttpObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        RxDisposableManager.getInstance().addDis(context,d);
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
        LogUtils.error(throwable!=null&&!TextUtils.isEmpty(throwable.getMessage())?throwable.getMessage():"请求失败");
        onAfter();
    }

    //执行方法之后
    protected void onAfter()
    {

    }

}
