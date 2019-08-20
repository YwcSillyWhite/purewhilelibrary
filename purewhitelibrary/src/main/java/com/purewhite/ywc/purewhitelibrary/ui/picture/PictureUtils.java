package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseSkipActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.activity.PictureSelectActivity;

import java.util.ArrayList;
import java.util.List;

public class PictureUtils {


    public static Builder buidler()
    {
        return new Builder();
    }









    public static class Builder
    {
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

        public Builder setImageList(List<String> imageList) {
            this.imageList = imageList;
            return this;
        }

        public Builder setPictureType(int pictureType) {
            this.pictureType = pictureType;
            return this;
        }

        public Builder setLineNum(int lineNum) {
            this.lineNum = lineNum;
            return this;
        }

        public Builder setPreview(boolean preview) {
            isPreview = preview;
            return this;
        }

        public Builder setPictureMaxNum(int pictureMaxNum) {
            this.pictureMaxNum = pictureMaxNum;
            return this;
        }

        public void build(Activity activity)
        {
            PictureManager.newInstance().init(imageList,pictureMaxNum,lineNum,pictureType,isPreview);
            Intent intent = new Intent(activity, PictureSelectActivity.class);
            activity.startActivity(intent);
            if (activity instanceof BaseSkipActivity)
            {
                ((BaseSkipActivity) activity).skipAnim(2);
            }
        }
    }




}
