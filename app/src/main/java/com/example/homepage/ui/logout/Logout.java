package com.example.homepage.ui.logout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.homepage.Login;
import com.example.homepage.MainActivity;
import com.example.homepage.R;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Logout");
        setContentView(R.layout.activity_logout);
        Intent r = new Intent(Logout.this, Login.class);
        startActivity(r);
        finish();
    }
}