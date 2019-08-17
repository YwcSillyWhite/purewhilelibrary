package com.purewhite.ywc.purewhitelibrary.ui.image;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.ui.image.activity.PictureSelectActivity;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.PictureBean;

import io.reactivex.annotations.NonNull;

public class PictureUtils{

    public static void intent(@NonNull Fragment fragment,PictureBean pictureBean, int requestCode)
    {
        Intent intent = new Intent(fragment.getContext(), PictureSelectActivity.class);
        intent.putExtra(PictureConfig.pictureBean,pictureBean==null?new PictureBean():pictureBean);
        fragment.startActivityForResult(intent,requestCode);
    }


    public static void intent(@NonNull Activity activity, PictureBean pictureBean, int requestCode)
    {
        Intent intent = new Intent(activity, PictureSelectActivity.class);
        intent.putExtra(PictureConfig.pictureBean,pictureBean==null?new PictureBean():pictureBean);
        activity.startActivityForResult(intent,requestCode);
    }

}
