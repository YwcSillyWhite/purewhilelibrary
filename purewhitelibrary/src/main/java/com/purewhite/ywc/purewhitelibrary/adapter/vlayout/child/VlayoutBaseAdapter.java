package com.purewhite.ywc.purewhitelibrary.adapter.vlayout.child;


import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemLongListener;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;

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

    //点击事件
    private OnItemListener onItemListener;
    private boolean isItemClick=true;
    public final void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public void setItemClick(boolean itemClick) {
        isItemClick = itemClick;
    }

    //长按事件
    protected OnItemLongListener onItemLongListener;
    private boolean isItemLongClick=true;
    public void setOnItemLongListener(OnItemLongListener onItemLongListener) {
        this.onItemLongListener = onItemLongListener;
    }
    public void setItemLongClick(boolean itemLongClick) {
        isItemLongClick = itemLongClick;
    }


    //布局集合
    private SparseIntArray layoutIds=new SparseIntArray();
    //添加布局
    protected final void addLayout(@LayoutRes int layoutId)
    {
        addLayout(0,layoutId);
    }
    protected final void addLayout(int itemType,@LayoutRes int layoutId)
    {
        if (layoutId!=0)
        {
            layoutIds.append(itemType,layoutId);
        }
    }
    protected final int obtianLayoutId(int itemType) {
        return layoutIds.get(itemType, R.layout.pure_adapter_error_layout);
    }





    public VlayoutBaseAdapter(List<T> list) {
        this.mData = list!=null?list:new ArrayList<T>();
    }



    @Override
    public final int getItemViewType(int position) {
        return obtianDataType(position);
    }

    protected  int obtianDataType(int position) {
        return super.getItemViewType(position);
    }


    /**
     * 创建布局
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onDataCreateViewHolder(parent,viewType,obtianLayoutId(viewType));
    }

    protected V onDataCreateViewHolder(@NonNull ViewGroup parent, int viewType,@LayoutRes int layoutIds)
    {
        View itemView = obtainView(parent,layoutIds);
        V v = createV(itemView);
        bindDataListener(v);
        return v;
    }

    //获取布局
    protected final View obtainView(ViewGroup viewGroup,int layoutIds)
    {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutIds,viewGroup,false);
    }

    //创建viewhold
    protected final V createV(View view) {
        return ((V) new BaseViewHolder(view));
    }

    protected final void bindDataListener(V holder) {
        if (holder==null)
            return;
        View itemView = holder.itemView;
        if (itemView==null)
            return;
        if (isItemClick&&onItemListener!=null)
        {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ClickUtils.clickable(view))
                    {
                        onItemListener.onClick(VlayoutBaseAdapter.this,view, holder.getLayoutPosition(),true);
                    }
                }
            });
        }
        if (isItemLongClick&&onItemLongListener!=null)
        {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return onItemLongListener.onClick(VlayoutBaseAdapter.this,view, holder.getLayoutPosition(),true);
                }
            });
        }



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

    }




    //赋值数据
    protected abstract void onData(V holder,int position,T t);







    //刷新数据
    public void flush(List<T> list)
    {
        if (mData.size()==0)
        {
            if (list!=null&&list.size()>0)
            {
                mData.addAll(list);
                notifyDataSetChanged();
            }
        }
        else
        {
            mData.clear();
            if (list!=null&&list.size()>0)
            {
                mData.addAll(list);
            }
            notifyDataSetChanged();
        }
    }

    //添加数据
    public void addData(List<T> list)
    {
        if (obtianDataCount()==0)
        {
            if (list!=null&&list.size()>0)
            {
                mData.addAll(list);
                notifyDataSetChanged();
            }
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
