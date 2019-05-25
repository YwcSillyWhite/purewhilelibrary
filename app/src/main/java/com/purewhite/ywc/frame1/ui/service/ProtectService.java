package com.purewhite.ywc.frame1.ui.service;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;

/**
 * 保护进程
 * @author yuwenchao
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ProtectService extends JobService {

    private Handler handler=new Handler()
    {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what)
            {
                case 0:
                    startService(new Intent(ProtectService.this,RunService.class));
                    break;
            }
        }
    };

    @Override
    public boolean onStartJob(JobParameters params) {
        handler.sendEmptyMessage(0);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        handler.removeCallbacksAndMessages(null);
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
}
