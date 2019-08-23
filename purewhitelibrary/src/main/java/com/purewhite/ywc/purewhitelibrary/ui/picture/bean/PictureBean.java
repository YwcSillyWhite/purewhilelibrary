package com.purewhite.ywc.purewhitelibrary.ui.picture.bean;

import java.util.ArrayList;
import java.util.List;

public class PictureBean<T> {
    //一次选择图片的数量
    protected int imageMax=9;
    //图片
    protected List<String> selectorList=new ArrayList<>();
    //图片框架类型
    protected int pictureType=0;
    //图片显示一行几个
    protected int lineNum=3;
    //是否显示预览
    protected boolean isPreview=false;
    //是否显示拍照,默认显示
    protected boolean isCamera=true;



    public T setImageMax(int imageMax) {
        this.imageMax = imageMax;
        return ((T) this);
    }

    public T setSelectorList(List<String> selectorList) {
        this.selectorList=selectorList!=null?selectorList:new ArrayList<>();
        return ((T) this);
    }

    public T setPictureType(int pictureType) {
        this.pictureType = pictureType;
        return ((T) this);
    }

    public T setLineNum(int lineNum) {
        this.lineNum = lineNum;
        return ((T) this);
    }

    public T setPreview(boolean preview) {
        isPreview = preview;
        return ((T) this);
    }

    public T setCamera(boolean camera) {
        isCamera = camera;
        return ((T) this);
    }




    public int getImageMax() {
        return imageMax>0?imageMax:9;
    }

    public List<String> getSelectorList() {
        return selectorList;
    }

    public int getPictureType() {
        return pictureType;
    }

    public int getLineNum() {
        return lineNum;
    }

    public boolean isPreview() {
        return isPreview;
    }

    public boolean isCamera() {
        return isCamera;
    }

    //是否选中
    public boolean isSelector(String path)
    {
        return selectorList.contains(path);
    }
}
