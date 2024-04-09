package com.example.module_login.ui;

import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import com.example.common.base.BaseActivity;
import com.example.module_login.databinding.ActivityLoginBinding;

//@Route(path = "/module_login/ui/LoginActivity")
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}