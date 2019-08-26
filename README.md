纯白框架V1.0.1
====
### 加入纯白框架
1.在项目以model接入purewhitelibrary框架<br>
2.在build.gradle加入（存在3方框架PhotoView）
```java
  maven { url "https://jitpack.io" } 
```
3.在build.gradle(app)加入(兼容了databinding) 
```java
   dataBinding {
        enabled = true
    }<br>
```
4.初始化 
```java
  /**
     * 框架初始化
     * @param application
     * @param retrofitBaseUri   retrofit默认框架
     * @param adaptiveWight     屏幕适配的dp
     */
 AppUtils.initLibrary(this,"",375);
```


### 拥有功能
###### adapter（vlayout【阿里出品】适配器，recyclerview adapter，pagerview adapter）
一.adapter（vlayout【全局加载，加载更多】，recyclerview【全局加载，加载更多，头尾】）<br>
Fullview【全局布局】，LoadView【加载布局】
```java
fullviewImp,和loadviewimp已设置，可以根据自己需求修改
也可以自己设置
 public final void setFullView(FullView fullView)；
 public  final void setLoadView(LoadView loadView);
```
OnFullListener【全局布局的重新加载监听】 OnLoadListener【加载更多的重新加载监听】
```java
只有设置这2个监听，全局加载和加载更多才有用
```
OnItemListener【item的点击事件】
```java
public interface OnItemListener {
    /**
     *
     * @param adapter  适配器
     * @param view     点击的view
     * @param position 点击数据的position
     * @param itemView 是否为itemview
     */
    void onClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView);
}
```
设置加载长度，默认是10
```java
 public final void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
```
head和foot
```java
    //添加头尾
    public final void addHeadView 
    //添加尾部
    public final void addFootView
    //删除头部
    public final void removeHeadView
    //删除尾部
    public final void removeFootView
```
addLayout 添加布局
```java
protected final void addLayout(int itemType,@LayoutRes int layoutId)
    {
        if (layoutId!=0)
        {
            layoutIds.append(itemType,layoutId);
        }
    }
```
适配器使用例子可以查看HomeChildFragment这个类<br><br>

二.pagerview(提供4种方式)
BaseFragmentAdapter，BaseFragmentAdapterImp，BasePagerAdapter，BasePagerAdapterImp

###### 下拉刷新
一ScrollSwipeLayout<br>
继承于SwipeRefreshLayout做了一些滑动冲突处理<br><br>
二ScrollPtrLayout<br>
继承于BasePtrFrameLayout


###### BarUtils（状态栏处理）
可以查看这个类对应的方法，本项目是延伸到状态栏下处理的

###### 构建者模式创建的（Bundler，dialog）
dialog可以查看dialogActivity对应的使用方法
```java
构建一个弹窗
          dialog_one= DialogUtils.builder()
                            .setContentView(R.layout.dialog_one)
                            .setAnim(WindowAnimStyle.bottom_anim_window)
                            .buildBottom(this);
```

###### 点击事件
我在底层已实现点击
```java
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener



    //点击事件
    public final void onClickView(View view) {
        if (ClickUtils.clickable(view))
        {
            onClickUtils(view);
        }
    }

    @Override
    public void onClick(View view) {
        //防止重复点击
        if (ClickUtils.clickable(view))
        {
            onClickUtils(view);
        }
    }

    protected void onClickUtils(View view)
    {

    }
```
2种方法实现点击<br>
1.xml android:onclick="onClickView"<br>
2.view.setOnclickListener(this)<br>
在onclickUtils处理点击事件


###### EventBus封装
```java
EventBusUtils 进行发送
 public static void post(int code)
    {
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setCode(code);
        EventBus.getDefault().post(baseEvent);
    }


    public static void post(String content,int code)
    {
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setCode(code);
        baseEvent.setContent(content);
        EventBus.getDefault().post(baseEvent);
    }

    public static void post(int code,Object object)
    {
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setCode(code);
        baseEvent.setData(object);
        EventBus.getDefault().post(baseEvent);
    }


    public static void post(int code,String content,Object object)
    {
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setCode(code);
        baseEvent.setContent(content);
        baseEvent.setData(object);
        EventBus.getDefault().post(baseEvent);
    }
```
在activity，fragment存在eventbus注册开关
```java
 //默认没有开启eventbus
    protected boolean isEventBus()
    {
        return false;
    }
```

###### fileUtils文件创建更严谨

###### permissonUtils权限
```java
权限使用
private PermissonCallBack permissonCallBack=new PermissonCallBack() {
        @Override
        public void onPermissonSuccess(int requestCode) {
            switch (requestCode)
            {
                case 1:
                    PictureUtils.buidler()
                            .setImageMax(6)
                            .setLineNum(3)
                            .setCamera(false)
                            .build(CameraActivity.this,1);
                    break;
                case 2:
                    PictureUtils.buidler()
                            .setImageMax(6)
                            .setLineNum(3)
                            .build(CameraActivity.this,1);
                    break;
                case 3:
                    PictureUtils.buidler()
                            .setImageMax(6)
                            .setLineNum(3)
                            .setSelectorList(list)
                            .build(CameraActivity.this,1);
                    break;
            }
        }

        @Override
        public void onPermissonRepulse(int requestCode, String... permisssons) {

        }
    };


    @Override
    protected void onClickUtils(View view) {
        switch (view.getId())
        {
            case R.id.left_img:
                backActivity();
                break;
            case R.id.open_one:
                startPermisson(1,permissonCallBack,Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.open_two:
                startPermisson(2,permissonCallBack,Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.open_three:
                startPermisson(3,permissonCallBack,Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;

        }

    }
```

###### SpUtils缓存
###### AdaptiveUtils今日头条适配
###### mvp文件
mvp文件里面存在功能<br>
1.mvp设置模式<br>
2.activity跳转和回退动画，查看BaseSkipActivity这个类
3.datading
4.bar和event等
######network文件
network文件存在功能<br>
1.okhttputils封装，可以直接使用OkNetUtils里面的方法<br>
2.retrofit和rxjava2封装，可以直使用RenetUtils里面的方法<br>
3.glide封装ImageLoader

###### 相册
```java
         PictureUtils.buidler()
                            .setImageMax(6)
                            .setLineNum(3)
                            .setCamera(false)
                            .build(CameraActivity.this,1);
                            
                                //一次选择图片的数量
    protected int imageMax=9;
    //图片
    protected List<String> selectorList=new ArrayList<>();
    //图片框架类型
    protected int pictureType=0;
    //图片显示一行几个
    protected int lineNum=3;
    //是否显示预览
    protected boolean isPreview=false;
    //是否显示拍照,默认显示
    protected boolean isCamera=true;
    
    
    获取图片地址
      List<String> list = PictureUtils.obtianArtwork(resultCode);
      
      压缩自己用鲁班压缩，下个大版本会加入鲁班压缩，和图片裁剪，视屏功能，我喜欢是先获取图片最后在处理图片压缩
```

###### PureViewPager（banner）
自己查看PagerActivity里面，下期准备用recycylerview封装


###### BottomLayout（下标选择）
自己可以查看CustomMainActivity使用，


###### 其他view
一.PureScrollView类iosScrollview<br> 
二.MaxHeightRecyclerView(最大高度recyclerview)<br>
三.RatioImageView等比例view<br>
四.PointLoadingView加载动画<br>


  

## 有什么不懂的，或者有好的提议可以添加qq 1075770029，或者加qq群127299409，欢迎大家一起讨论
