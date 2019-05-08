package com.purewhite.ywc.purewhitelibrary.network.okhttp.call;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static com.google.gson.internal.$Gson$Types.canonicalize;


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
        Type type = subclass.getGenericSuperclass();
        if (type instanceof Class) {
            LogUtils.debug("okhttp","不存在范型");
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        return canonicalize(actualTypeArguments[0]);
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

    public abstract void onSuccess(T t) throws Exception;



}
