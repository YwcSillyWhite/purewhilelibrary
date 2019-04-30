package com.purewhite.ywc.purewhitelibrary.config.permisson;

/**
 * @author yuwenchao
 */
public interface PermissonCallBack {
    //权限申请成功
    void onPermissonSuccess(int requestCode);

    void onPermissonRepulse(int requestCode, String... permisssons);
}
