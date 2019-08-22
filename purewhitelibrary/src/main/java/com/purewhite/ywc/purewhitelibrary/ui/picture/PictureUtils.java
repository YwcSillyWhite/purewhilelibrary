package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.app.Activity;
import android.content.Intent;

import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseSkipActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.activity.PictureSelectActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.PictureBean;

import java.util.ArrayList;
import java.util.List;

public class PictureUtils {


    public static Builder buidler()
    {
        return new Builder();
    }



    public static class Builder extends PictureBean<Builder>
    {
        public void build(Activity activity)
        {
            //设置值
            PictureManager.newInstance().setCamera(isCamera)
                    .setImageMax(imageMax)
                    .setLineNum(lineNum)
                    .setPictureType(pictureType)
                    .setPreview(isPreview)
                    .setSelectorList(selectorList);
            //进行跳转
            Intent intent = new Intent(activity, PictureSelectActivity.class);
            activity.startActivity(intent);
            if (activity instanceof BaseSkipActivity)
            {
                ((BaseSkipActivity) activity).skipAnim(1);
            }
        }
    }




}
