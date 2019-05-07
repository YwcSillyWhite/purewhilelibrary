package com.purewhite.ywc.frame1.bean.base;

import com.google.gson.annotations.SerializedName;

public class BaseBean<T> {
    private int code;
    private String msg;

    private int min_id;

    @SerializedName("data")
    private T t;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getMin_id() {
        return min_id;
    }

    public void setMin_id(int min_id) {
        this.min_id = min_id;
    }
}
