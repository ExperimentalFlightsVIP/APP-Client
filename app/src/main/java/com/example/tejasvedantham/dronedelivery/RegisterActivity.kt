package com.example.tejasvedantham.dronedelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    var usernameField: EditText = findViewById(R.id.r_usernameField)
    var passwordField: EditText = findViewById(R.id.r_passwordField)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun register(v: View?) {

    }
}