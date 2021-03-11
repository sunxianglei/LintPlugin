package com.sun.lintplugin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sun.lintplugin.util.LsLog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LsLog.d("tadsad", "jhk")
    }
}