package com.purewhite.ywc.purewhitelibrary.mvp.presenter;


import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.mvp.view.IBaseUiView;
import com.purewhite.ywc.purewhitelibrary.window.dialog.utils.DialogUtils;
import com.purewhite.ywc.purewhitelibrary.window.utils.WindowPureUtils;

import java.lang.ref.WeakReference;

/**
 *
 * @author yuwenchao
 * @date 2018/11/5
 * Presenter实现类
 */

public class BasePresenter<V extends IBaseUiView> implements IBasePresenter<V> {

    protected V mView;
    private WeakReference<V> vWeakReference;
    private DialogUtils dialogUtils;

    @Override
    public void addView(V view) {
        vWeakReference = new WeakReference<>(view);
        mView=vWeakReference.get();
        initPage();
    }

    @Override
    public void showLoad(String content) {
        if (dialogUtils == null)
        {
            dialogUtils=DialogUtils.builder()
                    .setContentView(R.layout.pure_dialog_load)
                    .setCanceled(false)
                    .setCanceledOnTouchOutside(false)
                    .buildDialog(mView.getContext())
                    .setSplace(0.8f,0);
        }
        dialogUtils.setTextView(R.id.dialog_content,content,false);
        dialogUtils.show();
    }

    @Override
    public void hideLoad() {
        if (dialogUtils!=null)
        {
            dialogUtils.dismiss();
        }
    }

    @Override
    public void deleteView() {
        //销毁dialog
        WindowPureUtils.onDialogDestory(dialogUtils);
        if (vWeakReference!=null)
        {
            vWeakReference.clear();
            mView=null;
        }
    }






    //当前页数
    protected int page;

    //初始化页数
    public void initPage()
    {
        page=1;
    }

    //设置页数
    public void setPage(int count)
    {
        page=count;
    }

    //自增
    public void  autoPage()
    {
        page++;
    }

}
