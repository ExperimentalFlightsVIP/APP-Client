package com.example.tejasvedantham.dronedelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    lateinit var usernameField: EditText
    lateinit var passwordField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        usernameField = findViewById(R.id.r_usernameField)
        passwordField = findViewById(R.id.r_passwordField)
    }

    fun register(v: View?) {

    }
}