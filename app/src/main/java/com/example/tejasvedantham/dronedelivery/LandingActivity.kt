package com.example.tejasvedantham.dronedelivery

import android.content.Intent
import android.accounts.AccountManager
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.security.AccessController.getContext

class LandingActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        val logoImage = findViewById<View>(R.id.logoView) as ImageView
        logoImage.setImageResource(R.drawable.logo)
        logoImage.adjustViewBounds = true
        auth = Firebase.auth
    }

    fun login(v: View?) {
        val user_name_area = findViewById<EditText>(R.id.usernameField)
        val password_area = findViewById<EditText>(R.id.passwordField)
        val username = user_name_area.text
        val password = password_area.text
        Log.i("username", username.toString())
        Log.i("password", password.toString())
        auth.signInWithEmailAndPassword(username.toString(), password.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, move on to UI/maps section
                    Snackbar.make(v!!, "Login Successful", Snackbar.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, do nothing
                }
            }
        // [END sign_in_with_email]
    }

    fun register(v: View?) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}