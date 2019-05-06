package com.purewhite.ywc.frame1.ui.fragment.home;

import android.net.Uri;

import com.purewhite.ywc.frame1.bean.ShopBean;
import com.purewhite.ywc.frame1.network.OkHttp;
import com.purewhite.ywc.frame1.network.UrlUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;

import java.util.HashMap;
import java.util.Map;

public class HomePresenter extends PresenterImp<HomeContract.UiView> implements HomeContract.Presenter {

    @Override
    public void obtianShop() {
        Map<String,String> map=new HashMap<>();
        map.put("nav","1");
        map.put("cid","0");
        map.put("back","10");
        map.put("min_id","1");
        OkHttp.get(UrlUtils.shop, map, new OkCallBack<ShopBean>() {
            @Override
            public void onFail(Exception e) {

            }

            @Override
            public void onSuccess(ShopBean shopBean) {

            }
        });
    }
}
