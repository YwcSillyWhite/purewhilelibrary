package com.purewhite.ywc.frame1.ui.fragment.home.child;

import com.purewhite.ywc.frame1.bean.ShopBean;
import com.purewhite.ywc.frame1.bean.base.BaseBean;
import com.purewhite.ywc.frame1.network.UrlUtils;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.OkNetUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeChildPresenter extends PresenterImp<HomeChildContract.UiView>
        implements HomeChildContract.Presenter {

    @Override
    public void obtianShopList(int position, final boolean flush) {
        if (flush)
            page=1;
        Map<String,String> map=new HashMap<>();
        map.put("nav","1");
        map.put("cid",position+"");
        map.put("back","10");
        map.put("min_id",page+"");
        OkNetUtils.get(UrlUtils.shop, map, new OkCallBack<BaseBean<List<ShopBean>>>() {
            @Override
            public void onFail(Exception e) {
                LogUtils.error("okhttp",e.toString());
            }

            @Override
            public void onSuccess(BaseBean<List<ShopBean>> baseBean) {
                if (baseBean.getCode()==1)
                {
                    page=baseBean.getMin_id();
                    List<ShopBean> beanList = baseBean.getT();
                    if (beanList!=null&&beanList.size()>0)
                    {
                        mView.getAdapter().addDataFlush(flush?1:0,beanList);
                    }
                }
            }

            @Override
            public void onAfter() {
                super.onAfter();
                mView.respon(flush);
            }
        });
    }

}
