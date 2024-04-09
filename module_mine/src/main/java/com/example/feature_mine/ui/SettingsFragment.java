package com.example.feature_mine.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.module_mine.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}