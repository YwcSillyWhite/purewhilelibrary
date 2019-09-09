package com.purewhite.ywc.purewhitelibrary.config;

import java.lang.reflect.ParameterizedType;

/**
 * 范型
 */
public class PatternUtils {

    public static <T> T getT(Object o, int i) {
        try
        {
            return ((Class<T>) ((ParameterizedType) (o.getClass().getGenericSuperclass())).getActualTypeArguments()[i]).newInstance();
        }
        catch (Exception e)
        {

        }
        return null;
    }
}
