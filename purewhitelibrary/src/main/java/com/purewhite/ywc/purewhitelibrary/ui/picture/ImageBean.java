package com.purewhite.ywc.purewhitelibrary.ui.picture;

/**
 * @author yuwenchao
 */
public class ImageBean {

    public ImageBean(String path, String name, Long time) {
        this.path = path;
        this.name = name;
        this.time = time;
    }

    //地址
    private String path;
    //名字
    private String name;
    //时间
    private Long time;

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public Long getTime() {
        return time;
    }
}
