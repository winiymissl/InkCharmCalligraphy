package com.example.miaobi;

import android.util.Log;

import com.example.common.base.BaseApplication;

/**
 * @Author winiymissl
 * @Date 2024-04-03 10:14
 * @Version 1.0
 */
public class MiaoBiApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                // 在这里处理异常
                Log.d("MyApplication", "UncaughtException: " + throwable.toString());

                // 退出应用
                System.exit(1);
            }
        });
    }
}
