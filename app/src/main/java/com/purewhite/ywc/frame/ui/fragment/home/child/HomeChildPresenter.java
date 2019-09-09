package com.purewhite.ywc.frame.ui.fragment.home.child;

import com.purewhite.ywc.frame.bean.ShopBean;
import com.purewhite.ywc.frame.bean.base.BaseBean;
import com.purewhite.ywc.frame.network.UrlUtils;
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
        OkNetUtils.get(getView().getFragment(),UrlUtils.shop, map, new OkCallBack<BaseBean<List<ShopBean>>>() {
            @Override
            public void onSuccess(BaseBean<List<ShopBean>> baseBean) {
                if (baseBean.getCode()==1)
                {
                    page=baseBean.getMin_id();
                    List<ShopBean> beanList = baseBean.getT();
                    if (beanList!=null&&beanList.size()>0)
                    {
                        handlerAdapter(true,beanList);
                        return;
                    }
                }
                handlerAdapter(true,null);
            }

            @Override
            public void onFail(Exception e) {
                handlerAdapter(false,null);
            }


            @Override
            public void onAfter() {
                super.onAfter();
                getView().respon(flush);
            }

            private void handlerAdapter(boolean network,List<ShopBean> list) {
                getView().getAdapter().addDataFlush(flush,list,network);
            }
        });
    }

}
