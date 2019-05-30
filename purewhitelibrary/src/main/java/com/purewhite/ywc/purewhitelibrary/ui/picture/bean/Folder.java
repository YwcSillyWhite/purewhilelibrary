package com.purewhite.ywc.purewhitelibrary.ui.picture.bean;

import java.util.List;

/**
 * @author yuwenchao
 */
public class Folder {

    private String name;

    private List<ImageBean> imageBeanList;

    public Folder(String name, List<ImageBean> imageBeanList) {
        this.name = name;
        this.imageBeanList = imageBeanList;
    }

    public String getName() {
        return name;
    }


    public List<ImageBean> getImageBeanList() {
        return imageBeanList;
    }

}
