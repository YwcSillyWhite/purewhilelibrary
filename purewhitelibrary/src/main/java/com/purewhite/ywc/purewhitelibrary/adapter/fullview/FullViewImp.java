package com.purewhite.ywc.purewhitelibrary.adapter.fullview;

import android.view.View;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.NetWorkUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;


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
    public void setItemView(View itemView) {
        super.setItemView(itemView);
        if (onFullListener!=null)
        {
            itemView.findViewById(R.id.network_again).setOnClickListener(new OnSingleListener() {
                @Override
                public void onSingleClick(View v) {
                    if (onFullListener!=null&&NetWorkUtils.isConnected())
                    {
                        setFullState(LODA,true);
                        onFullListener.again();
                    }
                }
            });
        }

    }
}
