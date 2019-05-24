package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview;



import com.purewhite.ywc.purewhitelibrary.adapter.bean.BaseTypeBean;

import java.util.List;

/**
 *
 * @author yuwenchao
 * @date 2018/11/17
 * 使用数据里面的
 */

public abstract class BindTypeAdapter<T extends BaseTypeBean> extends BindAdapter<T>{


    public BindTypeAdapter() {
        super();
    }
    public BindTypeAdapter(List<T> list) {
        super(list);
    }

    @Override
    protected int obtianDataType(int position) {
        return obtainT(position)!=null?obtainT(position).getTypeBean():0;
    }

}
