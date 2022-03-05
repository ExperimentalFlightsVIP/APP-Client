package com.example.tejasvedantham.dronedelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Settings"
        setContentView(R.layout.activity_settings)
    }
}