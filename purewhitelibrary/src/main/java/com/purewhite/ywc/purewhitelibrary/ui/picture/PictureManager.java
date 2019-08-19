package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.config.ToastUtils;

import java.util.ArrayList;
import java.util.List;

//选中图片管理类
public class PictureManager {
    private static PictureManager pictureSelectorManager;
    private int imageMax=9;
    private List<String> selectorList=new ArrayList<>();

    //图片框架类型
    private int pictureType=0;
    //每行显示几个
    private int lineNum=3;
    //是否显示预览
    private boolean isPreview=false;

    public static PictureManager newInstance() {
        if (pictureSelectorManager==null)
        {
            synchronized (PictureManager.class)
            {
                if (pictureSelectorManager==null)
                {
                    pictureSelectorManager=new PictureManager();
                }
            }
        }
        return pictureSelectorManager;
    }

    //初始化图片管理器
    public void init(List<String> selectorList, int imageMax, int lineNum, int pictureType, boolean isPreview) {
        this.selectorList=selectorList!=null?selectorList:new ArrayList<>();
        this.imageMax=imageMax<0?9:imageMax;
        this.lineNum=lineNum;
        this.pictureType=pictureType;
        this.isPreview=isPreview;
    }


    public int getPictureType() {
        return pictureType;
    }

    public int getLineNum() {
        return lineNum>=2?lineNum:3;
    }

    public boolean isPreview() {
        return isPreview;
    }

    public List<String> getSelectorList() {
        return selectorList;
    }

    //添加或者删除图片
    public boolean alterImage(String path)
    {
        if (TextUtils.isEmpty(path))
        {
            return false;
        }
        if (selectorList.contains(path))
        {
            selectorList.remove(path);
            return true;
        }
        else
        {
            if (selectorList.size()>=imageMax)
            {
                ToastUtils.show("最多选择"+imageMax+"张图片");
                return false;
            }
            else
            {
                selectorList.add(path);
                return true;
            }

        }
    }


    //是否选中
    public boolean isSelector(String path)
    {
        return selectorList.contains(path);
    }
}
