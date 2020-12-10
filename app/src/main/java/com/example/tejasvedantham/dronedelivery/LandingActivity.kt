package com.example.tejasvedantham.dronedelivery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        val logoImage = findViewById<View>(R.id.logoView) as ImageView
        logoImage.setImageResource(R.drawable.logo)
        logoImage.adjustViewBounds = true
    }

    fun login(v: View?) {
        Snackbar.make(v!!, "Login Successful", Snackbar.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}