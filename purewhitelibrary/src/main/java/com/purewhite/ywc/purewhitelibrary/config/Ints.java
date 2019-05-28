package com.purewhite.ywc.purewhitelibrary.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组int型转list数据
 * @author yuwenchao
 */
public class Ints {

    public static  List<Integer> asList(int ...a) {
        List<Integer> list = null;
        if (a!=null&&a.length>0)
        {
            list=new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                list.add(a[i]);
            }
        }
        return list;
    }
}
