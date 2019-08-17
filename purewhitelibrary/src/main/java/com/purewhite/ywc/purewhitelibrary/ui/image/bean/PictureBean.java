package com.purewhite.ywc.purewhitelibrary.ui.image.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PictureBean implements Serializable {


    //图片地址
    private List<String> imageList=new ArrayList<>();
    //图片框架类型
    private int pictureType=0;
    //每行显示几个
    private int lineNum=3;
    //是否显示预览
    private boolean isPreview=false;
    //图片最大显示数量
    private int pictureMaxNum=9;

    public List<String> getImageList() {
        return imageList;
    }

    public int getPictureType() {
        return pictureType;
    }

    public int getLineNum() {
        return lineNum>0?lineNum:3;
    }

    public boolean isPreview() {
        return isPreview;
    }

    public int getPictureMaxNum() {
        return pictureMaxNum;
    }
}
