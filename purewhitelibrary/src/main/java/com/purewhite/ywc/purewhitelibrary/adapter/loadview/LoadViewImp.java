package com.purewhite.ywc.purewhitelibrary.adapter.loadview;


import com.purewhite.ywc.purewhitelibrary.R;

/**
 *
 * @author yuwenchao
 * @date 2018/11/19
 */

public class LoadViewImp extends LoadView{

    @Override
    public int getLayoutId() {
        return R.layout.loadview;
    }

    @Override
    int getLoadId() {
        return R.id.load;
    }

    @Override
    int getNetworkId() {
        return R.id.network;
    }

    @Override
    int getDataId() {
        return R.id.data;
    }

}
