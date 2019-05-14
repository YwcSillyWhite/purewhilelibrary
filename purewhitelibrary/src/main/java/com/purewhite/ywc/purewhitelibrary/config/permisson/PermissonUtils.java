package com.purewhite.ywc.purewhitelibrary.config.permisson;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.purewhite.ywc.purewhitelibrary.app.activity.ActivityUtils;
import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.app.activity.StartBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yuwenchao
 */
public class PermissonUtils {

    public static final String CAMERA = Manifest.permission.CAMERA;
    public static final String CALL = Manifest.permission.CALL_PHONE;
    public static final String WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String READ = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String CONTACTS = Manifest.permission.READ_CONTACTS;
    public static final String LOCATION = Manifest.permission.LOCATION_HARDWARE;
    //应用安装权限
    public static final String REQUEST= Manifest.permission.REQUEST_INSTALL_PACKAGES;

    //默认请求
    public static final int DEFAULT=10001;
    //安装权限
    public static final int per_request=10002;


    /**
     * 判断之前有没有拒绝过这个权限
     * @param context
     * @param permission
     * @return 拒绝过一次就返回false，拒绝过二次以上返回true
     */
    public static boolean judgePermission(Context context, String permission) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission))
            return true;
        else
            return false;
    }


    public static boolean judgePermissions(Context context,String... permissions)
    {
        for (String permission:permissions)
        {
            if (judgePermission(context,permission))
            {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断权限集合体
     * @param object
     * @param permissons
     * @return 返回值sparseArray，长度大于1那么就存在权限为开启
     */
    public static List<String> checkPermissons(Object object, String... permissons)
    {
        List<String> list=new ArrayList<>();
        if (permissons==null||permissons.length<=0)
            return list;
        if (object instanceof Activity)
        {
            for (int i = 0; i < permissons.length; i++) {
                if (ContextCompat.checkSelfPermission(((Activity) object),permissons[i])!=PackageManager.PERMISSION_GRANTED)
                {
                    list.add(permissons[i]);
                }
            }
        }
        else if (object instanceof Fragment)
        {
            for (int i = 0; i < permissons.length; i++) {
                if (ContextCompat.checkSelfPermission(((Fragment) object).getContext(),permissons[i])!=PackageManager.PERMISSION_GRANTED)
                {
                    list.add(permissons[i]);
                }
            }
        }
        return list;
    }

    /**
     * 开启多个权限
     * @param context
     * @param permissons
     * @param requestCode
     */
    public static void requestMorePermissions(Context context, List<String> permissons,int requestCode)
    {
        if (permissons!=null&&permissons.size()>0)
        {
            String[] permisssons_string = permissons.toArray(new String[permissons.size()]);
            requestMorePermissions(context,requestCode,permisssons_string);
        }
    }

    /**
     * 开启多个权限
     * @param context
     * @param requestCode
     * @param permissons
     */
    public static void requestMorePermissions(Context context,int requestCode,String... permissons)
    {
        if (permissons!=null&&permissons.length>0)
        {
            ActivityCompat.requestPermissions(((Activity) context), permissons, requestCode);
        }
    }


    /**
     * 启动权限
     * @param context
     * @param permissons
     */
    public static void startPermissons(Context context,PermissonCallBack permissonCallBack, String... permissons)
    {
        startPermissons(context,permissonCallBack,DEFAULT,permissons);
    }

    /**
     * 启动权限
     * @param context
     * @param permissons
     */
    public static void startPermissons(Context context,PermissonCallBack permissonCallBack,int requestCode, String... permissons)
    {
        if (permissonCallBack==null||permissons==null)
        {
            throw new UnsupportedOperationException("permissonCallBack or permissons can not null");
        }
        List<String> list = checkPermissons(context, permissons);
        if (list.size()<=0)
        {
            permissonCallBack.onPermissonSuccess(requestCode);
        }
        else
        {
            requestMorePermissions(context,list,requestCode);
        }
    }

    /**
     * 权限返回处理
     * @param context
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @param permissonCallBack
     */
    public static void permissionsResult(Context context,int requestCode, String[] permissions,int[] grantResults,PermissonCallBack permissonCallBack)
    {
        if (grantResults!=null&&grantResults.length > 0)
        {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                {
                    List<String> list = checkPermissons(context, permissions);
                    if (list.size()<=0)
                    {
                        permissonCallBack.onPermissonSuccess(requestCode);
                    }
                    else
                    {
                        permissonCallBack.onPermissonRepulse(requestCode,  list.toArray(new String[list.size()]));
                    }
                    return;
                }
            }
        }
        permissonCallBack.onPermissonSuccess(requestCode);
    }


    /**
     * 跳转到权限页面
     */
    public static void intentPremession()
    {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", AppUtils.getContext().getPackageName(), null));
        AppUtils.getContext().startActivity(intent);
    }


    /**
     * android 8.0以上安装权限
     * @param context
     * @return
     */
    public static boolean canRequestPackageInstalls(Context context)
    {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            return context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }

    /**
     * 跳转安装权限页面
     * @param context
     * @param requestCode
     */
    public static void intentPackageInstalls(Context context,int requestCode)
    {
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES
                ,Uri.parse("package:"+context.getPackageManager()));
        ActivityUtils.startDefalut().startActivity(intent,context,requestCode);
    }

    /**
     * 启动安装权限页面
     * @param context
     * @param requestCode
     * @return
     */
    public static boolean startPackageInstalls(Context context,int requestCode)
    {
        if (!canRequestPackageInstalls(context))
        {
            intentPackageInstalls(context,requestCode);
            return false;
        }
        return true;
    }




}
