# 纯白基本框架
# 使用
1.在项目以model接入purewhitelibrary框架

2.在build.gradle加入

  maven { url "https://jitpack.io" } 

3.在build.gradle(app)加入
 
   dataBinding {
        enabled = true
    }



## 版本1.0.0
* 框架模式 mvp模式封装
* activity的封装(base,skip,bind,mvp)
* fragment的封装(base,skip,bind,mvp)
* 状态栏封装barUtils
* 相册封装 pic文件里面
* 屏幕适配封装（根据今日头头条）
* 适配器封装（recyclerview，vlayout，pager）
加载更多，头尾，加载状态等
* 网络请求封装（rxjava+retrofit，okhttp，glide）
* 自定义view封装（PureScrollView，最大高度recyclerview，pointLoadview等）
* Permisson封装PermissonUtils<br>
* dialog封装（window文件）
* 常用工具类封装（config文件）
* Toast封装ToastUtils <br>
* bottom封装类似radiobutton BottomLayout和BottomMenu <br>

#### 有什么不懂的，或者有好的提议可以添加qq 2048525395，欢迎大家一起讨论
