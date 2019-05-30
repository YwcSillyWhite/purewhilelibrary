package com.purewhite.ywc.purewhitelibrary.ui.picture.bean;

/**
 * @author yuwenchao
 */
public class ImageBean {

    public ImageBean(String path, String name, Long time_token) {
        this.path = path;
        this.name = name;
        this.time_token = time_token;
    }

    //地址
    private String path;
    //名字
    private String name;
    //时间
    private Long time_token;

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public Long getTime() {
        return time_token;
    }
}
