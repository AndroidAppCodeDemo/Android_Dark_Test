/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xiaxl.dark_test;

import android.util.Log;

import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.BuildCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ThemeHelper {


    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ThemeHelper.Mode.DEFAULT, ThemeHelper.Mode.LIGHT, ThemeHelper.Mode.DARK})
    public @interface Mode {
        String DEFAULT = "default";
        String LIGHT = "light";
        String DARK = "dark";
    }


    public static int getDefaultNightMode() {
        int model = AppCompatDelegate.getDefaultNightMode();
        Log.e("xiaxl: ", "getDefaultNightMode: " + model);
        return model;
    }

    /**
     * 应用主题
     *
     * @param theme
     */
    public static void applyTheme(@ThemeHelper.Mode String theme) {
        Log.e("xiaxl: ", "--- applyTheme ---");
        switch (theme) {
            // 亮色
            case ThemeHelper.Mode.LIGHT: {
                Log.e("xiaxl: ", "MODE_NIGHT_NO");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            }
            // 深色
            case ThemeHelper.Mode.DARK: {
                Log.e("xiaxl: ", "MODE_NIGHT_YES");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            }
            default: {
                // 跟随 系统设置（系统深色模式，则深色模式；系统浅色模式，则浅色模式）
                if (BuildCompat.isAtLeastQ()) {
                    Log.e("xiaxl: ", "MODE_NIGHT_FOLLOW_SYSTEM");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
                // 省电模式时 深色模式；
                // 非省点模式时 浅色模式
                else {
                    Log.e("xiaxl: ", "MODE_NIGHT_AUTO_BATTERY");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                }
                break;
            }
        }
    }
}
