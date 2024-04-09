package com.example.module_navigation;

import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.common.base.BaseActivity;
import com.example.module_navigation.databinding.ActivityNavigationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends BaseActivity<ActivityNavigationBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        getWindow().setReenterTransition(new Explode());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView view = findViewById(R.id.navView);
        NavigationUI.setupWithNavController(view, navController);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        supportFinishAfterTransition();
//        finishAfterTransition();
    }
}