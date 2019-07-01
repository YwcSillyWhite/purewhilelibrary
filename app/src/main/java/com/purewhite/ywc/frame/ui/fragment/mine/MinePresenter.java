package com.purewhite.ywc.frame.ui.fragment.mine;

import com.purewhite.ywc.frame.bean.ShopBean;
import com.purewhite.ywc.frame.bean.base.BaseBean;
import com.purewhite.ywc.frame.network.UrlUtils;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.ReNetUtils;
import com.purewhite.ywc.purewhitelibrary.network.rxjava.HttpObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MinePresenter extends PresenterImp<MineContract.UiView> implements MineContract.Presenter {
    @Override
    public void obtianShop() {
        showLoad("数据加载中....");
        Map<String,Object> map=new HashMap<>();
        map.put("nav",1);
        map.put("cid",0);
        map.put("back",10);
        map.put("min_id",1);
        ReNetUtils.getT(UrlUtils.shop_re, map, new BaseBean<List<ShopBean>>()
                ,new HttpObserver<BaseBean<List<ShopBean>>>(mView.getContext()) {
            @Override
            protected void onSuccess(BaseBean<List<ShopBean>> baseBean) {
                if (baseBean.getCode()==1&&baseBean.getT()!=null&&baseBean.getT().size()>0)
                {
                    LogUtils.debug("请求成功");
                }

            }
        });
    }
}
