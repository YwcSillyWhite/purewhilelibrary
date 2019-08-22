package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.config.ToastUtils;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.PictureBean;

import java.util.ArrayList;
import java.util.List;

//选中图片管理类
public class PictureManager2 {

    private static PictureManager2 pictureSelectorManager;
    public static PictureManager2 newInstance() {
        if (pictureSelectorManager==null)
        {
            synchronized (PictureManager2.class)
            {
                if (pictureSelectorManager==null)
                {
                    pictureSelectorManager=new PictureManager2();
                }
            }
        }
        return pictureSelectorManager;
    }


    //选中position
    private int seletorPosition;
    //图片地址
    private List<ImageBean> list;

    public int getSeletorPosition() {
        return seletorPosition;
    }

    public PictureManager2 setSeletorPosition(int seletorPosition) {
        this.seletorPosition = seletorPosition;
        return this;
    }

    public List<ImageBean> getList() {
        return list;
    }

    public PictureManager2 setList(List<ImageBean> list) {
        this.list = list!=null?list:new ArrayList<>();
        return this;
    }
}
