package com.purewhite.ywc.purewhitelibrary.window.popup;

import android.view.View;
import android.widget.PopupWindow;

public class BasePopupWindow extends PopupWindow {

    public BasePopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }


    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
    }


    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }


}
