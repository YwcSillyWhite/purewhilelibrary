package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseSkipActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.activity.PictureSelectActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.PictureBean;

import java.util.List;

public class PictureUtils {


    public static Builder buidler()
    {
        return new Builder();
    }


    public static List<String> obtianArtwork(int result)
    {
        if (result==PictureConfig.back_picture_to_)
        {
            final List<String> selectorList = PictureManager.newInstance().getSelectorList();
            return selectorList;
        }
        return null;
    }



    public static class Builder extends PictureBean<Builder>
    {
        public void build(Activity activity,int requestCode)
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
            activity.startActivityForResult(intent,requestCode);
            if (activity instanceof BaseSkipActivity)
            {
                ((BaseSkipActivity) activity).skipAnim(1);
            }
        }

        public void build(Fragment fragment, int requestCode)
        {
            //设置值
            PictureManager.newInstance().setCamera(isCamera)
                    .setImageMax(imageMax)
                    .setLineNum(lineNum)
                    .setPictureType(pictureType)
                    .setPreview(isPreview)
                    .setSelectorList(selectorList);
            //进行跳转
            Intent intent = new Intent(fragment.getActivity(), PictureSelectActivity.class);
            fragment.startActivityForResult(intent,requestCode);
            if (fragment.getActivity() instanceof BaseSkipActivity)
            {
                ((BaseSkipActivity) fragment.getActivity()).skipAnim(1);
            }
        }
    }




}
