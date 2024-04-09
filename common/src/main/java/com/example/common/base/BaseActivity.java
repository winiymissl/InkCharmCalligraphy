package com.example.common.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.eventbus.BindEventBus;
import com.example.common.eventbus.EventBusUtils;

/**
 * @Author winiymissl
 * @Date 2024-04-03 21:07
 * @Version 1.0
 */
public class BaseActivity<T> extends AppCompatActivity {
    protected T binding;

    protected void initDagger() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
            EventBusUtils.register(this);
        }
        initDagger();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
            EventBusUtils.unregister(this);
        }
    }
}
