package com.purewhite.ywc.frame.ui.activity.mine;

import android.os.Handler;
import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivitySocketBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePurePresenter;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkhttpBuilder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class SocketActivity extends MvpActivity<ActivitySocketBinding, BasePurePresenter> {

    private Handler handler=new Handler();
    private WebSocket webSocket;
    private void sendHandler()
    {
        handler.removeCallbacks(null);
        handler.postDelayed(runnable,10000);
    }
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            LogUtils.debug("心跳");
            send("心跳检测",true);
        }
    };

    private void send(String content,boolean detection)
    {
        if (webSocket!=null)
        {
            webSocket.send(content);
            if (detection)
            {
                sendHandler();
            }
        }
    }

    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.socket_connect:
                    break;
            }
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_socket;
    }


    @Override
    protected void initView() {
        initTwo();

    }

    private void initTwo() {
        //标签用来取消常链接
        OkHttpUtils.get().tag(this).url("ws:47.105.113.174:9502?action=create_room&userId=26&auctionId=58").newWebSocket(OkhttpBuilder.longLink, new WebSocketListener() {

            //已断开链接
            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
                LogUtils.debug("onClosed");
            }


            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosing(webSocket, code, reason);
                LogUtils.debug("onClosing");
            }

            //链接失败
            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                //链接失败
//                handler.removeCallbacks(null);
//                initTwo();
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                super.onMessage(webSocket, text);
                LogUtils.debug("onMessage");
                sendHandler();
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
                LogUtils.debug("onMessage");
            }

            //链接成功
            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
                SocketActivity.this.webSocket=webSocket;
                LogUtils.debug("onOpen");
            }
        });
    }






    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.newInstance().cancleTag(this);
        handler.removeCallbacks(runnable);
    }
}
