package com.purewhite.ywc.purewhitelibrary.network.okhttp.call;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author yuwenchao
 */
public abstract class OkCallBack<T> {

    public Type mType;
    public OkCallBack() {
        mType=getSuperclassTypeParameter(getClass());
    }

    /**
     *获取范型
     * @param subclass
     * @return
     */
    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    /**
     * 之前
     */
    public void onBefore()
    {

    }

    /**
     * 之后
     */
    public void onAfter()
    {

    }


    public abstract void onFail(Exception e);

    public abstract void onSuccess(T t);

    public static OkCallBack CALLBACK_DEFAULT=new OkCallBack()
    {

        @Override
        public void onFail(Exception e) {

        }

        @Override
        public void onSuccess(Object o) {

        }
    };

}
