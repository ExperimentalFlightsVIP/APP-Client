package com.example.tejasvedantham.dronedelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        ImageView logoImage = (ImageView) findViewById(R.id.imageView);
        logoImage.setImageResource(R.drawable.logo);
        logoImage.setAdjustViewBounds(true);
    }

    public void login(View v) {
        Snackbar.make(v, "Login Successful", Snackbar.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
