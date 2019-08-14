package com.purewhite.ywc.purewhitelibrary.window.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.window.base.WindowViewUtils;

public class PopupWindowUtils extends WindowViewUtils {

    private BasePopupWindow basePopupWindow;
    public PopupWindowUtils(BasePopupWindow basePopupWindow, View.OnClickListener onClickListener, View view) {
        super(view,onClickListener);
        this.basePopupWindow = basePopupWindow;

    }



    public static class Builder
    {
        private int layoutId;
        private int wight = ViewGroup.LayoutParams.MATCH_PARENT;
        private int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        private int anim;
        private boolean outsideTouchable;
        private View.OnClickListener onClickListener;

        public Builder setContentView(@LayoutRes int layoutId)
        {
            this.layoutId=layoutId;
            return this;
        }

        public Builder setWight(int wight) {
            this.wight = wight;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setAnim(int anim) {
            this.anim = anim;
            return this;
        }

        public Builder setOutsideTouchable(boolean outsideTouchable) {
            this.outsideTouchable = outsideTouchable;
            return this;
        }

        public Builder setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public PopupWindowUtils Builder(Context context)
        {
            View view = LayoutInflater.from(context).inflate(layoutId!=0?layoutId: R.layout.pure_window_error, null);
            BasePopupWindow basePopupWindow = new BasePopupWindow(view, wight, height);
            if (outsideTouchable)
            {
                basePopupWindow.setOutsideTouchable(true);
                basePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            if (anim!=0)
            {
                basePopupWindow.setAnimationStyle(anim);
            }
            return new PopupWindowUtils(basePopupWindow,onClickListener,view);

        }
    }
}
