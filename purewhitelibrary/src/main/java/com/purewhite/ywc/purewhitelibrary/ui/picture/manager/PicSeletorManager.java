package com.purewhite.ywc.purewhitelibrary.ui.picture.manager;



import androidx.annotation.NonNull;

import com.purewhite.ywc.purewhitelibrary.config.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片选中管理类
 * @author yuwenchao
 */
public class PicSeletorManager {
    //图片选择最大长度
    private int picSize=9;
    public void setPicSize(int picSize) {
        this.picSize = picSize;
    }

    public static volatile PicSeletorManager seletorPicManager;
    public static PicSeletorManager newInstance() {
        if (seletorPicManager==null)
        {
            synchronized (PicSeletorManager.class)
            {
                if (seletorPicManager==null)
                {
                    seletorPicManager=new PicSeletorManager();
                }
            }
        }
        return seletorPicManager;
    }

    private List<String> listPath=new ArrayList<>();
    private PicSeletorManager() {
    }

    //图片是否选中
    public boolean isSelectorPic(@NonNull String picPath)
    {
        return listPath.contains(picPath);
    }


    public int obtainPicCount()
    {
        return listPath.size();
    }

    public String obtainPicContent()
    {
        return obtainPicCount()+"/"+picSize;
    }


    /**
     *  //处理数据
     * @param picPath 图片地址
     * @return 是否刷新
     */
    public boolean solvePic(@NonNull String picPath)
    {
        if (isSelectorPic(picPath))
        {
            listPath.remove(picPath);
        }
        else
        {
            if (listPath.size()<picSize)
            {
                listPath.add(picPath);
            }
            else
            {
                ToastUtils.show("最多只能选择"+picSize+"张图片");
                return false;
            }

        }
        return true;
    }


    /**
     * 获取list数据列表
     * @return
     */
    public List<String> getListPath()
    {
        return listPath;
    }

    /**
     * 最后数据清空
     */
    public void onDestory()
    {
        picSize=9;
        listPath.clear();
    }
}
