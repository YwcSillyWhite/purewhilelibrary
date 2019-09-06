package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemLongListener;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter基础类
 * @param <T>
 * @param <V>
 */
public abstract class BaseAdapter<T,V extends BaseViewHolder> extends RecyclerView.Adapter<V> {

    public BaseAdapter(List<T> mData) {
        this.mData = mData!=null?mData:new ArrayList<>();
    }

    //数据
    private List<T> mData;
    //获取对应position的t
    public final T obtainT(int position)
    {
        if (position<obtainDataCount())
        {
            return mData.get(position);
        }
        return null;
    }
    //数据长度
    protected  int obtainDataCount()
    {
        return mData.size();
    }

    /**
     * 获取数据
     * @return
     */
    public final List<T> obtainData() {
        return mData;
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
    protected final int obtianLayoutId(int itemType)
    {
        return layoutIds.get(itemType, R.layout.pure_adapter_error_layout);
    }



    //点击事件
    protected OnItemListener onItemListener;
    private boolean isItemClick=true;
    public void setOnItemListener(OnItemListener onItemListener) {
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

    //创建viewhold
    protected final V createV(View view) {
        return ((V) new BaseViewHolder(view));
    }

    //获取布局
    protected final View obtainView(ViewGroup viewGroup,int layoutIds)
    {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutIds,viewGroup,false);
    }
    //设置点击时间
    protected final void bindDataListener(final V viewhold) {
        if (viewhold==null)
            return;
        View itemView = viewhold.itemView;
        if (itemView==null)
            return;
        if (isItemClick&&onItemListener!=null)
        {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ClickUtils.clickable(view))
                    {
                        int dataPosition = obtainDataPosition(viewhold.getLayoutPosition());
                        onItemListener.onClick(BaseAdapter.this,view, dataPosition,true);
                    }
                }
            });
        }
        if (isItemLongClick&&onItemLongListener!=null)
        {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int dataPosition = obtainDataPosition(viewhold.getLayoutPosition());
                    return onItemLongListener.onClick(BaseAdapter.this,view,dataPosition,true);
                }
            });
        }
    }

    //获取data数据
    protected int obtainDataPosition(int adapterPosition)
    {
        return adapterPosition;
    }

    //获取adapter数据
    protected int obtainAdapterPosition(int dataPosition)
    {
        return dataPosition;
    }



    /**
     * 绑定布局
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        onDataBindView(holder,position,holder.getItemViewType());
    }

    protected void onDataBindView(V holder,int dataPosition,int itemViewType)
    {
        onData(holder,dataPosition,obtainT(dataPosition),itemViewType);
    }
    //赋值数据
    protected abstract void onData(V holder,int dataPosition,T t,int itemViewType);




    /**
     * recyclerview的长度
     * @return
     */
    @Override
    public int getItemCount() {
        return obtainDataCount();
    }





    //刷新
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


    //刷新某个数据
    public void flushPosition(int dataPosition)
    {
        notifyItemChanged(obtainAdapterPosition(dataPosition));
    }


    //添加数据
    public void addDatas(List<T> list)
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
            if (list!=null&&list.size()>0)
            {
                mData.addAll(list);
                notifyItemRangeInserted(obtainAdapterPosition(obtainDataCount()-list.size()), list.size());
            }
        }
    }


    //添加单个数据
    public void addData(T t)
    {
        if (t!=null)
        {
            mData.add(t);
            notifyItemRangeInserted(obtainAdapterPosition(obtainDataCount()-1),1);
        }
    }

    //删除某个数据
    public void removePosition(int dataPosition)
    {
        if (dataPosition<obtainDataCount())
        {
            mData.remove(dataPosition);
            notifyItemRemoved(obtainAdapterPosition(dataPosition));
        }
    }
}
