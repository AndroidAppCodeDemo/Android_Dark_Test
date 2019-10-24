
# Android Q 深色主题举例

![在这里插入图片描述](https://img-blog.csdnimg.cn/20191024162846475.gif)

了解深色主题如何应用，第一手资料是 [官方文档](https://developer.android.com/guide/topics/ui/look-and-feel/darktheme) 与 相应的 [Google Sample](https://github.com/googlearchive/android-DarkTheme) 

官方文档：DayNight — Adding a dark theme to your app: 
[https://medium.com/androiddevelopers/appcompat-v23-2-daynight-d10f90c83e94](https://medium.com/androiddevelopers/appcompat-v23-2-daynight-d10f90c83e94)
官方文档：Dark theme：
[https://developer.android.com/preview/features/darktheme](https://developer.android.com/preview/features/darktheme)
官方案例：android-DarkTheme
[https://github.com/googlearchive/android-DarkTheme](https://github.com/googlearchive/android-DarkTheme)

## 一、深色主题简介

从Support Library 23.2.0 开始，AppCompat 新增了主题：`Theme.AppCompat.DayNight`

+ 其允许APP在 `深色主题` 和 `亮色主题` 之间切换
+ 可以大幅减少耗电量（`OLED显示屏`的设备上，`深色主题`较`亮色主题` 有更加持久的续航能力）
+ 改善弱视以及对强光敏感的用户的可视性
+ 让所有人都可以在光线较暗的环境中更轻松地使用设备，从而提升用户体验

而从Android Q（10.0）开始，Android设置中新增  `深色主题背景` 切换按钮（**设置-显示-深色主题背景**）。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191024163043367.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly94aWF4bC5ibG9nLmNzZG4ubmV0,size_16,color_FFFFFF,t_70)
因此Android App支持夜间模式，需提上开发日程了...


## 二、如何使用

#### 2.1 主题设置

APP主题需继承`Theme.AppCompat.DayNight`或 `Theme.MaterialComponents.DayNight`，以下为代码举例。

```xml
<!-- Base application theme. -->
<style name="AppTheme" parent="Theme.AppCompat.DayNight">
    <!-- Customize your theme here. -->
</style>
```
#### 2.2 监听Android Q 系统主题变化

若需要监听系统主题，例如**设置-显示-深色主题背景** 切换动作。

Activity添加`android:configChanges="uiMode"`
```
<activity
        android:name=".MyActivity"
        android:configChanges="uiMode" />
```

Activity中重写 onConfigurationChanged 方法

```java
/**
 * Android系统设置中 "设置-显示-深色主题背景" 切换后，回调该方法
 */
@(CSDN)Override
public void onConfigurationChanged(@NonNull Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    int mSysThemeConfig = newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK;
    switch (mSysThemeConfig) {
	    // 亮色主题
        case Configuration.UI_MODE_NIGHT_NO:
            break;
        // 深色主题
        case Configuration.UI_MODE_NIGHT_YES:
            break;
    }
}
```

#### 2.3 切换App主题

App 中切换应用主题，首先需调用`AppCompatDelegate.setDefaultNightMode(int mode)`方法，并调用`recreate()`方法使更改生效。

```java
// 切换到 深色主题
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
// 需调用 recreate() ，从而使更改生效
recreate();
```

`AppCompatDelegate.setDefaultNightMode(int mode)`方法有四个参数选项，具体介绍如下：

```java
// 亮色主题
ThemeHelper.Mode.LIGHT
// 暗色主题
ThemeHelper.Mode.DARK
// 跟随 系统设置（系统深色模式，则深色模式；系统浅色模式，则浅色模式）
AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
// 省电模式时 深色模式；非省点模式时 浅色模式
AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
```

#### 2.4 监听App主题变化

若需要监听App的主题变更，可重写以下方法

```java
/**
 * 回调当前应用的使用主题
 */
@Override
protected void onNightModeChanged(int mode) {
    super.onNightModeChanged(mode);
    switch (mode) {
		    // 亮色主题
        case AppCompatDelegate.MODE_NIGHT_NO:
            break;
            // 暗色主题
        case AppCompatDelegate.MODE_NIGHT_YES:
            break;
            // 省电模式时 深色模式；非省点模式时 浅色模式
        case AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY:
            break;
            // 跟随 系统设置（系统深色模式，则深色模式；系统浅色模式，则浅色模式）
        case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
            break;
    }
}
```

#### 2.5 自定义背景颜色

+ 新建`values-night`文件夹
![在这里插入图片描述](https://img-blog.csdnimg.cn/20191024162808708.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly94aWF4bC5ibG9nLmNzZG4ubmV0,size_16,color_FFFFFF,t_70)

+ 将`values/styles.xml` 拷贝到`values-night/styles.xml`
`values/styles.xml`与`values-night/styles.xml`主题代码举例如下

values/styles.xml
```xml
<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.DayNight">
        <!-- Customize your theme here. -->
    </style>
</resources>
```

values-night/styles.xml
```xml
<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light">
        <!-- Customize your theme here. -->
    </style>
</resources>
```


+ 将`values/colors.xml` 拷贝到`values-night/colors.xml`
更改`test_text_bg`属性的颜色值

values/colors.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">@android:color/white</color>
    <color name="test_layout_bg">@android:color/white</color>
    <color name="test_text">@android:color/black</color>
    <color name="test_text_bg">#008577</color>
</resources>
```
values-night/colors.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">@android:color/black</color>
    <color name="test_layout_bg">@android:color/black</color>
    <color name="test_text">@android:color/white</color>
    <color name="test_text_bg">#D81B60</color>
</resources>
```

## 三、OK 完事大吉

案例源码下载地址：
[https://github.com/AndroidAppCodeDemo/Android_Dark_Test](https://github.com/AndroidAppCodeDemo/Android_Dark_Test)


![在这里插入图片描述](https://img-blog.csdnimg.cn/20191024162846475.gif)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20191024162901687.gif)




