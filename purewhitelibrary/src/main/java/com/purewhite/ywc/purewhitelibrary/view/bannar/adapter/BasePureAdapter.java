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

    private List<T> realList;
    private List<T> list;
    private boolean isPalette=false;

    public void setPalette(boolean palette) {
        isPalette = palette;
    }

    private List<T> handerList(List<T> list)
    {
        realList.clear();
        if (list==null)
        {
            list=new ArrayList<>();
        }
        else
        {
            realList.addAll(list);
            if (list.size()>1)
            {
                T endT = list.get(0);
                T staetT = list.get(list.size() - 1);
                list.add(0,staetT);
                list.add(endT);
            }
        }
        return list;
    }

    private SparseArray<View> sparseArray;
    public BasePureAdapter(List<T> list) {
        this.realList=new ArrayList<>();
        this.list = handerList(list);
        sparseArray=new SparseArray<>();
    }

    //list真实长度
    public int getRealCount()
    {
        return realList.size();
    }

    public boolean isReal()
    {
        return getRealCount()>1;
    }

    public int getRealPosition(int position)
    {
        if (getCount()>1)
        {
            int realPosition;
            if (position==0)
            {
                realPosition=getCount()-2;
            }
            else if (getCount()-1==position)
            {
                realPosition=1;
            }
            else
            {
                realPosition=position;
            }
            return realPosition-1;
        }
        else
        {
            return position;
        }
    }

    @Override
    public int getCount() {
        return getRealCount()>1?list.size():getRealCount();
    }

    public T obtianT(int position)
    {
        if (position<getCount())
        {
            return list.get(position);
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
        T t = obtianT(position);
        View view = sparseArray.get(position);
        if (view==null)
        {
            view=obtianView(container,position,t);
            sparseArray.put(position,view);
        }
        container.addView(view);
        return view;
    }


    public abstract View obtianView(ViewGroup container, int position, T t);



    public void setImageView(ImageView imageView,String uri,int position)
    {
        if (isPalette&&isReal())
        {
            final int realPosition = getRealPosition(position);
            ImageLoader.newInstance().obtianBitmap(uri,new BitmapImageViewTarget(imageView)
            {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                    PureViewPalette.newInstance().putColor(realPosition,resource);
                }
            });
        }
        else
        {
            ImageLoader.newInstance().init(imageView,uri);
        }
    }


    @Override
    public final void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = sparseArray.get(position);
        if (view!=null)
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent!=null)
            {
                parent.removeView(view);
            }
        }
    }


    public void flush(List<T> list)
    {
        this.list=handerList(list);
        sparseArray.clear();
        notifyDataSetChanged();
    }



}
