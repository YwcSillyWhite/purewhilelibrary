package com.purewhite.ywc.purewhitelibrary.network.okhttp.call;

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
        if (type==null) {
            return null;
        }
        if (type instanceof ParameterizedType)
        {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments!=null&&actualTypeArguments.length>0)
            {
                return canonicalize(actualTypeArguments[0]);
            }
        }
        return null;
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
