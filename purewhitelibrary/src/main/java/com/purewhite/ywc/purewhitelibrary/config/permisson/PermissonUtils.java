package com.purewhite.ywc.purewhitelibrary.config.permisson;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public class PermissonUtils {
    //是否activity
    private boolean isActivity;
    private Activity activity;
    private Fragment fragment;
    private PermissonCallBack permissonCallBack;
    private int requestCode;
    private PermissonUtils(Activity activity,PermissonCallBack permissonCallBack) {
        this.activity=activity;
        this.permissonCallBack=permissonCallBack;
        isActivity=true;
    }

    private PermissonUtils(Fragment fragment,PermissonCallBack permissonCallBack)
    {
        this.fragment=fragment;
        this.permissonCallBack=permissonCallBack;
        isActivity=false;
    }

    public static PermissonUtils with(Activity activity,PermissonCallBack permissonCallBack)
    {
        return new PermissonUtils(activity,permissonCallBack);
    }

    public static PermissonUtils with(Fragment fragment,PermissonCallBack permissonCallBack)
    {
        return new PermissonUtils(fragment,permissonCallBack);
    }






    public void startPermisson(int requestCode,String... permissons)
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            List<String> permissonList = judgeStartPermissons(permissons);
            if (permissonList!=null&&permissonList.size()>0)
            {
                this.requestCode=requestCode;
                requestPermissions(permissonList);
            }
            else
            {
                permissonCallBack.onPermissonSuccess(requestCode);
            }
        }
        else
        {
            permissonCallBack.onPermissonSuccess(requestCode);
        }

    }




    /**
     * 判断是否存在权限
     * @param permissons
     * @return
     */
    private List<String> judgeStartPermissons(String... permissons)
    {
        List<String> list=new ArrayList<>();
        if (permissons==null||permissons.length<=0)
            return list;
        for(String permisson:permissons)
        {
            if (!havaPermissos(permisson))
            {
                list.add(permisson);
            }
        }
        return list;
    }

    //是否存在权限
    private boolean havaPermissos(String permisson)
    {
        if (isActivity)
        {
           return ContextCompat.checkSelfPermission(activity,permisson)==PackageManager.PERMISSION_GRANTED;
        }
        else
        {
            return ContextCompat.checkSelfPermission(fragment.getContext(),permisson)==PackageManager.PERMISSION_GRANTED;
        }
    }


    /**
     * 开启多个权限
     * @param permissonList
     */
    private void requestPermissions(List<String> permissonList)
    {
        String[] permisssons = permissonList.toArray(new String[permissonList.size()]);
        requestPermissions(permisssons);
    }

    /**
     * 开启多个权限
     * @param permissons
     */
    private void requestPermissions(String... permissons)
    {
        if (permissons!=null&&permissons.length>0)
        {
            if (isActivity)
            {
                ActivityCompat.requestPermissions(activity, permissons, requestCode);
            }
            else
            {
                fragment.requestPermissions(permissons,requestCode);
            }
        }
    }


    /**
     * 处理权限
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void disposePermissions(int requestCode,String[] permissions,int[] grantResults)
    {
        if (this.requestCode==requestCode)
        {
            List<String> permissonList=new ArrayList<>();
            if (grantResults!=null&&grantResults.length>0)
            {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i]!=PackageManager.PERMISSION_GRANTED)
                    {
                        LogUtils.debug("per",permissions[i]);
                        permissonList.add(permissions[i]);
                    }
                }
            }
            if (permissonList.size()>0)
            {
                permissonCallBack.onPermissonRepulse(requestCode,permissonList.toArray(new String[permissonList.size()]));

            }
            else
            {
                permissonCallBack.onPermissonSuccess(requestCode);
            }
        }
    }

}
