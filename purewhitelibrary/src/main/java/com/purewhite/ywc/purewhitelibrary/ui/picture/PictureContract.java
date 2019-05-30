package com.purewhite.ywc.purewhitelibrary.ui.picture;

import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;
import com.purewhite.ywc.purewhitelibrary.mvp.view.BaseUiView;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;

import java.util.List;

/**
 * @author yuwenchao
 */
public class PictureContract {

    interface ViewUi extends BaseUiView
    {
        void editImg(List<Folder> folders);
    }

    interface Presenter extends BasePresenter<ViewUi>
    {
        void gainImg();
    }
}
