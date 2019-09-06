package com.purewhite.ywc.purewhitelibrary.adapter.vlayout.child;


import com.purewhite.ywc.purewhitelibrary.adapter.bean.BaseTypeBean;

import java.util.List;

/**
 * @author yuwenchao
 */
public abstract class VlayoutBindTypeAdapter<T extends BaseTypeBean> extends VlayoutBindAdapter<T> {

    public VlayoutBindTypeAdapter() {
    }

    public VlayoutBindTypeAdapter(List<T> list) {
        super(list);
    }

    @Override
    protected int obtianDataType(int position) {
        return obtain(position)!=null?obtain(position).getTypeBean():0;
    }

}
