package com.purewhite.ywc.frame1.ui.fragment.home;

import com.purewhite.ywc.frame1.bean.ShopBean;
import com.purewhite.ywc.frame1.bean.base.BaseBean;
import com.purewhite.ywc.frame1.network.UrlUtils;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.config.ToastUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkNetUtils;
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
        OkNetUtils.get(UrlUtils.shop, map, new OkCallBack<BaseBean>() {
            @Override
            public void onFail(Exception e) {
                LogUtils.error("okhttp",e.toString());
            }

            @Override
            public void onSuccess(BaseBean baseBean) {
                if (baseBean.getCode()==1)
                {
                    ToastUtils.show("请求成功");
                }
            }


        });
    }
}
