package com.purewhite.ywc.frame.ui.activity.mine;

import android.os.Handler;
import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivitySocketBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkhttpBuilder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class SocketActivity extends MvpActivity<ActivitySocketBinding,PresenterImp> {
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    private Handler handler=new Handler();
    private WebSocket webSocket;
    private void sendHandler()
    {
        handler.removeCallbacks(null);
        handler.postDelayed(runnable,15000);
    }
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            send("心跳检测",true);
        }
    };

    private void send(String content,boolean detection)
    {
        if (webSocket!=null)
        {
            webSocket.send("心跳检测");
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
            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                super.onMessage(webSocket, text);
                sendHandler();
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
                SocketActivity.this.webSocket=webSocket;
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
