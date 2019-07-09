package com.purewhite.ywc.purewhitelibrary.adapter.vlayout.child;


import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwenchao
 */
public abstract class VlayoutBaseAdapter<T,V extends BaseViewHolder> extends DelegateAdapter.Adapter<V> {
    /**
     * LinearLayoutHelper: 线性布局
     * GridLayoutHelper: Grid布局， 支持横向的colspan
     * FixLayoutHelper: 固定布局，始终在屏幕固定位置显示
     * ScrollFixLayoutHelper: 固定布局，但之后当页面滑动到该图片区域才显示, 可以用来做返回顶部或其他书签等
     * FloatLayoutHelper: 浮动布局，可以固定显示在屏幕上，但用户可以拖拽其位置
     * ColumnLayoutHelper: 栏格布局，都布局在一排，可以配置不同列之间的宽度比值
     * SingleLayoutHelper: 通栏布局，只会显示一个组件View
     * OnePlusNLayoutHelper: 一拖N布局，可以配置1-5个子元素
     * StickyLayoutHelper: stikcy布局， 可以配置吸顶或者吸底
     * StaggeredGridLayoutHelper: 瀑布流布局，可配置间隔高度/宽度
     * @return
     */

    private List<T> mData;

    private OnItemListener onItemListener;
    public final void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    //本身是否可以点击
    private Boolean parentClick=true;
    public final void setParentClick(Boolean parentClick) {
        this.parentClick = parentClick;
    }

    @Override
    public final int getItemViewType(int position) {
        return obtianDataType(position);
    }

    protected  int obtianDataType(int position) {
        return super.getItemViewType(position);
    }


    public List<T> obtainData()
    {
        return mData;
    }

    public T obtain(int position)
    {
        if (position<mData.size())
        {
            return mData.get(position);
        }
        return null;
    }


    public VlayoutBaseAdapter(List<T> list) {
        this.mData = list!=null?list:new ArrayList<T>();
    }

    @NonNull
    @Override
    public final V onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        V viewHolder = onCreateData(viewGroup, viewType);
        return viewHolder;
    }

    @Override
    public final void onBindViewHolder(@NonNull V v, int i) {

    }

    @Override
    public final int getItemCount() {
        return obtianDataCount();
    }

    public int obtianDataCount()
    {
        return mData!=null?mData.size():0;
    }

    @Override
    protected  final void onBindViewHolderWithOffset(V holder, int position, int offsetTotal) {
        super.onBindViewHolderWithOffset(holder, position, offsetTotal);
        onData(holder,position,obtain(position));
        bindClick(holder,position);
    }

    private final void bindClick(final V holder, final int position) {
        if (holder==null&&!parentClick&&onItemListener==null) {
            return;
        }
        View view = holder.itemView;
        if (view==null)
        {
            return;
        }
        view.setId(Integer.MAX_VALUE);
        view.setOnClickListener(new OnSingleListener() {
            @Override
            public void onSingleClick(View v) {
                if (onItemListener!=null&&parentClick)
                {
                    onItemListener.onClick(VlayoutBaseAdapter.this,v,position,true);
                }
            }
        });
    }


    //赋值数据
    protected abstract void onData(V holder,int position,T t);


    protected abstract V onCreateData(ViewGroup parent, int viewType);


    //刷新数据
    public void flush(List<T> list)
    {
        mData=list!=null&&list.size()>0?list:new ArrayList<T>();
        notifyDataSetChanged();
    }

    //添加数据
    public void addData(List<T> list)
    {
        if (obtianDataCount()==0)
        {
            flush(list);
        }
        else
        {
            if (list!=null&&list.size()>0)
            {
                mData.addAll(list);
                notifyItemRangeInserted(obtianDataCount()-list.size() , list.size());
            }
        }
    }

    public void flushT(T t)
    {
        if (t==null)
        {
            return;
        }
        if (mData.size()>0)
        {
            mData.clear();
        }
        mData.add(t);
        notifyDataSetChanged();
    }

    /**
     * @param
     * @param list
     */
    public void addDataFlush(int page,List<T> list)
    {
        addDataFlush(page==1,list);
    }

    public void addDataFlush(boolean flush,List<T> list)
    {
        if (flush)
        {
            flush(list);
        }
        else
        {
            addData(list);
        }
    }

}
