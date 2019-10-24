# Android Q 深色主题

官方文档：
[https://developer.android.com/preview/features/darktheme](https://developer.android.com/preview/features/darktheme)

Android Q 提供全新的深色主题背景，既会应用于 Android 系统界面，也会应用于设备上运行的应用。

深色主题背景具有诸多优势：

+ 可以大幅减少耗电量（具体取决于设备的屏幕技术）。
+ 改善弱视以及对强光敏感的用户的可视性。
+ 让所有人都可以在光线较暗的环境中更轻松地使用设备。

在 Android Q 中，可以通过以下三种方法启用深色主题背景：

+ 用户可以通过新的系统设置（设置 -> 显示 -> 主题背景）启用深色主题背景。
+ 用户可以通过新的“快捷设置”图块从通知栏中快速切换主题背景（启用此主题背景后）。
+ 在 Pixel 设备上，启用省电模式的同时也会启用深色主题背景。其他 OEM 不一定支持这种行为。

## 使用举例

### 1、主题

主题继承`Theme.AppCompat.DayNight`或 `Theme.MaterialComponents.DayNight`

```xml
<!-- Base application theme. -->
<style name="AppTheme" parent="Theme.AppCompat.DayNight">
    <!-- Customize your theme here. -->
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimary</item>
    <item name="colorAccent">@color/colorPrimary</item>
    <item name="android:textColorPrimary">@color/test_text</item>
</style>
```

### 2、监听主题切换

Activity添加`android:configChanges="uiMode"`
```
<activity
        android:name=".MyActivity"
        android:configChanges="uiMode" />
```

Activity中重写 onConfigurationChanged 方法
```kotlin
override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    //
    val currentNightMode = newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK
    when (currentNightMode) {
        // Night mode is not active, we're using the light theme
        Configuration.UI_MODE_NIGHT_NO -> {

        }
        // Night mode is active, we're using dark theme
        Configuration.UI_MODE_NIGHT_YES -> {

        }
    }
}
```

### 3、主动切换主题

```
AppCompatDelegate.setDefaultNightMode()
```

### 4、自定义背景颜色 举例

a、新建`values-night`文件夹

![在这里插入图片描述](https://img-blog.csdnimg.cn/20191023213713283.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly94aWF4bC5ibG9nLmNzZG4ubmV0,size_16,color_FFFFFF,t_70)

b、将`values/colors.xml` 拷贝到`values-night/colors.xml`
c、更改`test_bg`属性的颜色值

values/colors.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">#008577</color>
    <color name="test_text">#D81B60</color>

    <color name="test_bg">#D81B60</color>
</resources>
```
values-night/colors.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">#D81B60</color>
    <color name="test_text">#008577</color>

    <color name="test_bg">#008577</color>
</resources>
```

### 5、源码下载

[https://github.com/AndroidAppCodeDemo/Android_Dark_Test](https://github.com/AndroidAppCodeDemo/Android_Dark_Test)




### 6、OK 完成  大吉大利
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019102321373646.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly94aWF4bC5ibG9nLmNzZG4ubmV0,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191023213749893.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly94aWF4bC5ibG9nLmNzZG4ubmV0,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191023213802556.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly94aWF4bC5ibG9nLmNzZG4ubmV0,size_16,color_FFFFFF,t_70)





