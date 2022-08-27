package com.bignerdranch.android.testtask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bignerdranch.android.testtask.core.data.getAppMode
import com.bignerdranch.android.testtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("myLogs", "MainActivity onCreate")

        AppCompatDelegate.setDefaultNightMode(getAppMode(this))
    }
}