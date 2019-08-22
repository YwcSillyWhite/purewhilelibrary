package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.config.ToastUtils;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.PictureBean;

import java.util.ArrayList;
import java.util.List;

//选中图片管理类
public class PictureManager extends PictureBean<PictureManager> {

    private static PictureManager pictureSelectorManager;
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




    public int getImageMax() {
        return imageMax>0?imageMax:9;
    }

    public List<String> getSelectorList() {
        return selectorList;
    }

    public int getPictureType() {
        return pictureType>1?pictureType:3;
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




    /**
     * 添加删除图片
     * @param path
     * @return  true数据发生变化 ，false数据没有发生变化
     */
    public boolean alterImage(String path)
    {

        if (TextUtils.isEmpty(path))
        {
            return false;
        }
        if (isSelector(path))
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




}
