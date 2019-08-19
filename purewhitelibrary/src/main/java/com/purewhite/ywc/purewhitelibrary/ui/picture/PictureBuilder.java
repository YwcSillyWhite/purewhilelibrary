package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.content.Context;
import android.content.Intent;

import com.purewhite.ywc.purewhitelibrary.ui.picture.activity.PictureSelectActivity;

import java.util.ArrayList;
import java.util.List;

public class PictureBuilder {
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


    public PictureBuilder setImageList(List<String> imageList) {
        this.imageList = imageList;
        return this;
    }

    public PictureBuilder setPictureType(int pictureType) {
        this.pictureType = pictureType;
        return this;
    }

    public PictureBuilder setLineNum(int lineNum) {
        this.lineNum = lineNum;
        return this;
    }

    public PictureBuilder setPreview(boolean preview) {
        isPreview = preview;
        return this;
    }

    public PictureBuilder setPictureMaxNum(int pictureMaxNum) {
        this.pictureMaxNum = pictureMaxNum;
        return this;
    }

    public void build(Context context)
    {
        PictureManager.newInstance().init(imageList,pictureMaxNum,lineNum,pictureType,isPreview);
        Intent intent = new Intent(context, PictureSelectActivity.class);
        context.startActivity(intent);
    }
}
