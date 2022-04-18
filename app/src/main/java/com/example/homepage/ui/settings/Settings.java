package com.example.homepage.ui.settings;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.homepage.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // changes action bar title
        getSupportActionBar().setTitle("Settings");

        // check if frame layout is empty
        if (findViewById(R.id.nav_settings) != null) {
            if (savedInstanceState != null) {
                return;
            }
            // inflates the fragment
            getFragmentManager().beginTransaction().add(R.id.nav_settings, new SettingsFragment()).commit();
        }
    }
}