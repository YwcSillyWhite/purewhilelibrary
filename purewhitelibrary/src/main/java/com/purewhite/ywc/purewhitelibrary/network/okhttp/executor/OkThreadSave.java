package com.purewhite.ywc.purewhitelibrary.network.okhttp.executor;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * @author yuwenchao
 */
public class OkThreadSave {

    private static final OkThreadSave okThreadSave=newInstance();

    public static OkThreadSave newInstance() {
        return new OkThreadSave();
    }

    private Executor getExecutor()
    {
        return new MainExecutor();
    }

    public void executor(Runnable runnable)
    {
        getExecutor().execute(runnable);
    }

    static class MainExecutor implements Executor
    {
        private final Handler handler=new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable runnable) {
            handler.post(runnable);
        }
    }
}
