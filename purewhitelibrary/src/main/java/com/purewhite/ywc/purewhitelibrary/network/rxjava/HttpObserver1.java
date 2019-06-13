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
public abstract class HttpObserver1<T> implements Observer<T> {

    private Context context;
    private boolean showLoad;


    //默认是加载动画
    public HttpObserver1(Context context) {
        this(context,true);
    }

    public HttpObserver1(Context context,boolean showLoad) {
        this.context = context;
        this.showLoad=showLoad;
    }


    @Override
    public void onSubscribe(Disposable d) {
        showDilaog();
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
        hideDialog();
    }



    protected void showDilaog()
    {

    }

    protected void hideDialog()
    {

    }



}
