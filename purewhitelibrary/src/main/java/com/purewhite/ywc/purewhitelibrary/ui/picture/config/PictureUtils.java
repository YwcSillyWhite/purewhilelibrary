package com.purewhite.ywc.purewhitelibrary.ui.picture.config;

import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public class PictureUtils {

    /**
     * 获取文件list
     * @param imageBeanList
     * @return
     */
    public static List<Folder> getFolderList(List<ImageBean> imageBeanList)
    {
        List<Folder> folderList=new ArrayList<>();
        folderList.add(new Folder("全部图片",imageBeanList));
        if (imageBeanList!=null&&imageBeanList.size()>0)
        {
            for (int i = 0; i < imageBeanList.size(); i++) {
                ImageBean imageBean = imageBeanList.get(i);
                String folderName = getFolderName(imageBean.getName());
                if (!TextUtils.isEmpty(folderName))
                {
                    Folder folder = getFolder(folderName, folderList);
                    folder.getImageBeanList().add(imageBean);
                }
            }
        }
        return folderList;
    }


    /**
     * 获取folder
     * @param folderName
     * @param list
     * @return
     */
    private static Folder getFolder(String folderName,List<Folder> list)
    {
        //查看文件list存在这个文件名不
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(folderName))
            {
                return list.get(i);
            }
        }
        return new Folder(folderName, new ArrayList<ImageBean>());
    }


    /**
     * 获取文件夹名字
     * @param path
     * @return
     */
    private static String getFolderName(String path) {
        if (!TextUtils.isEmpty(path)) {
            String[] strings = path.split(File.separator);
            if (strings.length > 1) {
                return strings[strings.length - 2];
            }
        }
        return "";
    }


}
