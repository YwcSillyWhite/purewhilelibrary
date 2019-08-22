package com.purewhite.ywc.purewhitelibrary.ui.picture.bean;

import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureManager;

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
        this.selectorList = selectorList;
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
}
