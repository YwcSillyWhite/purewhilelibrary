package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.top;


import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

import java.lang.ref.WeakReference;

/**
 * 置顶帮助类
 * @author yuwenchao
 */
public class ScrollTopHelp {

    private WeakReference<View> weakReference;
    private boolean moveRun=false;
    private ViewPropertyAnimatorCompat moveIn;
    private ViewPropertyAnimatorCompat moveOut;

    public ScrollTopHelp(View view) {
        view.setVisibility(View.GONE);
        weakReference=new WeakReference<>(view);
    }


    public void moveIn()
    {
        View view = weakReference.get();
        if (!moveRun&&view!=null&&view.getVisibility()!=View.VISIBLE)
        {
            view.setVisibility(View.VISIBLE);
            int width = view.getWidth();
            float translationX = view.getTranslationX();
            if (translationX==0)
            {
                view.setTranslationX(width);
            }
            moveIn = ViewCompat.animate(view).translationX(0)
                    .withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {
                            moveRun = true;
                        }

                        @Override
                        public void onAnimationEnd(View view) {
                            moveRun = false;
                        }

                        @Override
                        public void onAnimationCancel(View view) {
                            moveRun = false;
                        }
                    });
            moveIn.start();
        }
    }


    public void moveOut()
    {
        View view = weakReference.get();
        if (!moveRun&&view!=null&&view.getVisibility()==View.VISIBLE)
        {
            int width = view.getWidth();
            moveOut = ViewCompat.animate(view).translationX(width)
                    .withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {
                            moveRun = true;
                        }

                        @Override
                        public void onAnimationEnd(View view) {
                            moveRun = false;
                            view.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(View view) {
                            moveRun = false;
                        }
                    });
            moveOut.start();
        }
    }


    public void release() {
        if (moveOut != null) {
            moveOut.cancel();
        }
        if (moveIn != null) {
            moveIn.cancel();
        }
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }
}
