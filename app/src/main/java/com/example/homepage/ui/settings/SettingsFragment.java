package com.example.homepage.ui.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.example.homepage.R;
import androidx.annotation.Nullable;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference_list);

    }
}
