package com.purewhite.ywc.purewhitelibrary.adapter.fullview;

import android.view.View;

import com.purewhite.ywc.purewhitelibrary.R;


public class FullViewImp extends FullView{

    @Override
    public int getLayoutId() {
        return R.layout.pure_fullview;
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

    @Override
    public int getClickLoadId() {
        return R.id.network_again;
    }

    @Override
    public void setItemView(View itemView) {

    }


}
