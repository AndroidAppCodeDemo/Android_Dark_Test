package com.xiaxl.dark_test;

import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {


    /**
     * UI
     */
    private TextView mThemeTv = null;
    private TextView mChangeTv = null;

    /**
     * 数据
     */
    // 当前 Android系统主题 配置
    // (举例 Configuration.UI_MODE_NIGHT_NO Configuration.UI_MODE_NIGHT_YES)
    private int mSysThemeConfig = -1;

    // 当前 APP主题 设置
    private int mAppThemeMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("xiaxl: ", "--- onCreate ---");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 当前 Android系统主题 配置
         * (举例 Configuration.UI_MODE_NIGHT_NO Configuration.UI_MODE_NIGHT_YES)
         */
        Configuration configuration = getResources().getConfiguration();
        mSysThemeConfig = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;


        /**
         * UI
         */
        mThemeTv = findViewById(R.id.theme_tv);
        mChangeTv = findViewById(R.id.change_tv);

        // 手动切换主题
        mChangeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 手动切换主题
                toggleTheme();
            }
        });
        //
        updateThemeInfo();

    }

    /**
     * 回调当前应用的使用主题
     */
    @Override
    protected void onNightModeChanged(int mode) {
        Log.e("xiaxl: ", "--- onNightModeChanged ---");
        super.onNightModeChanged(mode);

        mAppThemeMode = mode;
        switch (mAppThemeMode) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                Log.e("xiaxl: ", "MODE_NIGHT_NO");
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                Log.e("xiaxl: ", "MODE_NIGHT_YES");
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY:
                Log.e("xiaxl: ", "MODE_NIGHT_AUTO_BATTERY");
                break;
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                Log.e("xiaxl: ", "MODE_NIGHT_FOLLOW_SYSTEM");
                break;
        }
        // 更新主题配置信息
        updateThemeInfo();
    }

    /**
     * Android系统设置中 "设置-显示-深色主题背景" 切换后，回调该方法
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Log.e("xiaxl: ", "--- onConfigurationChanged ---");
        super.onConfigurationChanged(newConfig);

        mSysThemeConfig = newConfig.uiMode & Configuration.UI_MODE_NIGHT_MASK;

        Log.e("xiaxl: ", "mSysThemeConfig: " + mSysThemeConfig);

        switch (mSysThemeConfig) {
            // Night mode is not active, we're using the light theme
            case Configuration.UI_MODE_NIGHT_NO:
                Log.e("xiaxl: ", "UI_MODE_NIGHT_NO");
                break;
            // Night mode is active, we're using dark theme
            case Configuration.UI_MODE_NIGHT_YES:
                Log.e("xiaxl: ", "UI_MODE_NIGHT_YES");
                break;
        }

        // 更新主题配置信息
        updateThemeInfo();

    }


    public void updateThemeInfo() {
        // 新建StringBuffer
        StringBuffer sb = new StringBuffer();
        //
        switch (mSysThemeConfig) {
            case Configuration.UI_MODE_NIGHT_NO:
                sb.append("Android系统主题配置：");
                sb.append("亮色主题");
                sb.append("\n");
                sb.append("\n");
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                sb.append("Android系统主题配置：");
                sb.append("深色主题");
                sb.append("\n");
                sb.append("\n");
                break;
        }
        // 应用主题配置
        switch (mAppThemeMode) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                sb.append("当前APP主题设置：");
                sb.append("亮色主题");
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                sb.append("当前APP主题设置：");
                sb.append("深色主题");
                break;

            case AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY:
                sb.append("当前APP主题设置：");
                sb.append("\n");
                sb.append("省电模式时 深色模式");
                sb.append("\n");
                sb.append("非省点模式时 亮色模式");
                break;

            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                Log.e("xiaxl: ", "MODE_NIGHT_FOLLOW_SYSTEM");
                sb.append("当前APP主题设置：");
                sb.append("跟随系统");
                sb.append("\n");
                sb.append("系统深色模式，则深色模式；系统亮色模式，则亮色模式");
                break;


        }
        // 显示当前主题信息
        if (mThemeTv != null) {
            mThemeTv.setText(sb.toString());
        }
    }


    /**
     * 手动 切换 并应用主题
     */
    public void toggleTheme() {
        // 当前App 亮色
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            // 深色
            AppCompatDelegate appCompatDelegate = getDelegate();
            appCompatDelegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        // 当前App 深色
        else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            // 亮色
            AppCompatDelegate appCompatDelegate = getDelegate();
            appCompatDelegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        // 跟随系统
        else {
            switch (mSysThemeConfig) {
                // 系统 亮色
                case Configuration.UI_MODE_NIGHT_NO:
                    // 深色
                    AppCompatDelegate appCompatDelegate = getDelegate();
                    appCompatDelegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                // 系统 深色
                case Configuration.UI_MODE_NIGHT_YES:
                    // 亮色
                    appCompatDelegate = getDelegate();
                    appCompatDelegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
            }
        }
        // 应用主题
        recreate();

    }

}



