# 纯白基本框架
# 使用
1.在项目以model接入purewhitelibrary框架<br>
2.在build.gradle加入（存在3方框架PhotoView）<br>
  maven { url "https://jitpack.io" } 
3.在build.gradle(app)加入(兼容了databinding) <br>
   dataBinding {
        enabled = true
    }<br>
4.初始化 
```java
public static void initLibrary(@NonNull Application application, String retrofitBaseUri, int adaptiveWight){

        if (!TextUtils.isEmpty(retrofitBaseUri))
        {
            baseUri=retrofitBaseUri;

        }
        if (adaptiveWight>0)
        {
            adaptiveWightDp=adaptiveWight;
        }
        AppUtils.application =application;
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);

        //内存检测
        if (!LeakCanary.isInAnalyzerProcess(application)) {
            LeakCanary.install(application);
        }
    }
```


     




#### 有什么不懂的，或者有好的提议可以添加qq 2048525395，欢迎大家一起讨论
