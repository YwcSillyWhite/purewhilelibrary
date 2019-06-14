package com.purewhite.ywc.frame.config;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class ServiceUtils {

    //开启保护进程
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void startProtectService(Context context)
    {
//        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
//        JobInfo jobInfo;
//        ComponentName componentName = new ComponentName(context, ProtectService.class);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//        {
//            jobInfo = new JobInfo.Builder(1,componentName)
//                    .setBackoffCriteria(10000,JobInfo.BACKOFF_POLICY_LINEAR)//设置线性重试策略
//                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) //设置网络类型
//                    .setMinimumLatency(10000) //设置最小执行间隔
//                    .setOverrideDeadline(10000)//设置任务执行的最晚延迟时间
//                    .setPersisted(true)//设置重启后任务是否保留
//                    .build();
//        }
//        else
//        {
//            jobInfo = new JobInfo.Builder(1, componentName)
//                    .setBackoffCriteria(10000, JobInfo.BACKOFF_POLICY_LINEAR)//设置线性重试策略
//                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) //设置网络类型
//                    .setPeriodic(10000) //设置执行周期
//                    .setPersisted(true)//设置重启后任务是否保留
//                    .build();
//        }
//        jobScheduler.schedule(jobInfo);
    }
}
