package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.config.ToastUtils;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;
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




    //查看的图片地址
    private List<ImageBean> list;
    public PictureManager setList(List<ImageBean> list) {
        this.list = list!=null? list:new ArrayList<>();
        return this;
    }

    public List<ImageBean> getList() {
        return list;
    }

    //选中position
    private int seletorPosition;

    public int getSeletorPosition() {
        return seletorPosition;
    }

    public PictureManager setSeletorPosition(int seletorPosition) {
        this.seletorPosition = seletorPosition;
        return this;
    }
}
