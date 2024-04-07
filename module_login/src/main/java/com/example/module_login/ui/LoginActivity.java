package com.example.module_login.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.base.BaseActivity;
import com.example.module_login.databinding.ActivityLoginBinding;

//@Route(path = "/module_login/ui/LoginActivity")
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}