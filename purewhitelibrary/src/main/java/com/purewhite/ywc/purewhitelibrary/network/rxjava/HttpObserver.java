package com.purewhite.ywc.purewhitelibrary.network.rxjava;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.config.NetWorkUtils;

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
            onFail("数据为空");
        }
    }

    @Override
    public void onError(Throwable e) {
        if (!NetWorkUtils.isConnected())
        {
            onFail("网络异常");
        }
        else if (e instanceof SocketTimeoutException||e instanceof ConnectException)
        {
            onFail("请求超时");
        }
        else if (e instanceof HttpException)
        {
            onFail("服务器异常");
        }
        else
        {
            onFail(e.getMessage()!=null&&!e.getMessage().isEmpty()?e.getMessage():"请求失败");
        }
    }

    @Override
    public void onComplete() {
        onAfter();
    }

    protected abstract void onSuccess(T t);

    protected  void onFail(String content)
    {
        LogUtils.error(content);
        onAfter();
    }

    //执行方法之后
    protected void onAfter()
    {

    }

}
