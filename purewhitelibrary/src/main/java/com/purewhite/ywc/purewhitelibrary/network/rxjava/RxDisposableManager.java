package com.purewhite.ywc.purewhitelibrary.network.rxjava;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author yuwenchao
 * rx 的任务管理类
 */
public final class RxDisposableManager {
    private static volatile RxDisposableManager sInstance;
    private HashMap<Object, CompositeDisposable> mCompositeDisposables;
    private RxDisposableManager() {
        mCompositeDisposables = new HashMap<>();
    }
    public static RxDisposableManager getInstance() {
        if (sInstance == null) {
            synchronized (RxDisposableManager.class) {
                if (sInstance == null) {
                    sInstance = new RxDisposableManager();
                }
            }
        }
        return sInstance;
    }

    //添加订阅
    public void addDis(Object tag, Disposable subscription) {
        CompositeDisposable compositeDisposable = mCompositeDisposables.get(tag);
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
            mCompositeDisposables.put(tag, compositeDisposable);
        }
        compositeDisposable.add(subscription);
    }


    /**
     * 取消订阅
     * 下面两方法防止内存泄露
     * @param tag
     */
    public void removeDis(Object tag) {
        CompositeDisposable compositeDisposable = mCompositeDisposables.get(tag);
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
            mCompositeDisposables.remove(tag);
        }
    }


    public void clear() {
        Iterator<Map.Entry<Object, CompositeDisposable>> iterator = mCompositeDisposables.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<Object, CompositeDisposable> entry = iterator.next();
            CompositeDisposable compositeDisposable = entry.getValue();
            if (compositeDisposable!=null)
            {
                compositeDisposable.dispose();;
                compositeDisposable.clear();
                iterator.remove();
            }
        }
    }
}
