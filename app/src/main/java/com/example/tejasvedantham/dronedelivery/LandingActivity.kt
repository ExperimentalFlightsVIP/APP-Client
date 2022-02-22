package com.example.tejasvedantham.dronedelivery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LandingActivity : AppCompatActivity() {

    lateinit var usernameField: EditText
    lateinit var passwordField: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        val logoImage = findViewById<View>(R.id.logoView) as ImageView
        logoImage.setImageResource(R.drawable.logo)
        logoImage.adjustViewBounds = true

        usernameField = findViewById(R.id.usernameField)
        passwordField = findViewById(R.id.passwordField)

        auth = Firebase.auth
    }

    fun login(v: View?) {
        if (usernameField.text.isNotBlank() and passwordField.text.isNotBlank()) {
            auth.signInWithEmailAndPassword(usernameField.text.toString(), passwordField.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, "Login Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(baseContext, "Authentication failed: " + task.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            Toast.makeText(baseContext, "Username or password cannot be blank", Toast.LENGTH_SHORT).show()
        }
    }

    fun registerActivity(v: View?) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}