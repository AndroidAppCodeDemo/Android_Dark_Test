package com.xiaxl.dark_test

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


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
}
