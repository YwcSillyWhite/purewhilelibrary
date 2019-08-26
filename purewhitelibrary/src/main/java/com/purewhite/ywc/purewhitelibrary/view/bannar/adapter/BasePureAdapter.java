package com.purewhite.ywc.purewhitelibrary.view.bannar.adapter;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.view.bannar.palette.PureViewPalette;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePureAdapter<T> extends PagerAdapter {

    //与加载卡片数量
    private int cardNum=3;
    //是否取色
    private boolean isImagexPalette;

    public boolean isImagexPalette() {
        return isImagexPalette;
    }

    //布局
    private SparseArray<View> sparseArray=new SparseArray<>();
    //真实list
    private List<T> realList=new ArrayList<>();
    //假设的list
    private List<T> datalist=new ArrayList<>();
    //处理list
    private void handerList(List<T> list)
    {
        realList.clear();
        datalist.clear();
        sparseArray.clear();
        if (list!=null&&list.size()>0)
        {
            realList.addAll(list);
            datalist.addAll(list);
            int cir_num = cardNum / list.size();
            for (int i = 0; i < cir_num; i++) {
                addDataList(datalist,realList);
            }
        }
    }

    private void addDataList(List<T> datalist,List<T> realList)
    {
        for (int i = 0; i < realList.size(); i++) {
            datalist.add(realList.get(i));
        }
    }

    public BasePureAdapter(List<T> list,int cardNum,boolean isImagexPalette) {
        this.cardNum=cardNum;
        this.isImagexPalette=isImagexPalette;
        handerList(list);
    }

    public BasePureAdapter(List<T> list) {
        this(list,3,false);
    }

    //当前真实position
    public int getRealPosition(int position)
    {
        final int realCount = realList.size();
        if (realCount>0)
        {
            return position%realCount;
        }
        return 0;
    }

    //当前view的positon
    private int getDataPosition(int position)
    {
        final int dataCount = datalist.size();
        if (dataCount>0)
        {
            return position%dataCount;
        }
        return 0;
    }

    public boolean isRealTop(int realPosition)
    {
        return realPosition+1==realList.size();
    }

    //初始化位置
    public int initPosition()
    {
        final int size = realList.size();
        if (size>1)
        {
            int centerPosition = Integer.MAX_VALUE / 2;
            return centerPosition-centerPosition%size;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public int getCount() {
        return realList.size()>1?Integer.MAX_VALUE:realList.size();
    }

    //获取当前数据的postion
    public T obtianT(int realPosition)
    {
        if (realPosition<realList.size())
        {
            return realList.get(realPosition);
        }
        return null;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public final Object instantiateItem(@NonNull ViewGroup container, int position) {
        int dataPosition = getDataPosition(position);
        View view = sparseArray.get(dataPosition);
        if (view==null)
        {
            int realPosition = getRealPosition(position);
            view=obtianView(container,realPosition,obtianT(realPosition));
            sparseArray.put(dataPosition,view);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent!=null)
            {
                parent.removeView(view);
            }
        }
        container.addView(view);
        return view;
    }



    @Override
    public final void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        int dataPosition = getDataPosition(position);
        View view = sparseArray.get(dataPosition);
        if (view!=null)
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent!=null)
            {
                parent.removeView(view);
            }
        }
    }


    public abstract View obtianView(ViewGroup container, int realPosition, T t);



    public void setImageView(ImageView imageView,String uri,int realPosition)
    {
        if (isImagexPalette)
        {
            ImageLoader.newInstance().obtianBitmap(uri,new BitmapImageViewTarget(imageView) {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    imageView.setImageBitmap(resource);
                    PureViewPalette.newInstance().putColor(realPosition,resource);
                }
            });
        }
        else
        {
            ImageLoader.newInstance().init(imageView,uri);
        }
    }




    public void flush(List<T> list)
    {
        handerList(list);
        notifyDataSetChanged();
    }



}
