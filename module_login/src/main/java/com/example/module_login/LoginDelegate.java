package com.example.module_login;


import android.util.Log;

import com.example.common.base.IApplicationDelegate;
import com.example.common.base.ViewManager;
import com.example.module_login.ui.login.LoginFragment;

/**
 * @Author winiymissl
 * @Date 2024-04-05 12:37
 * @Version 1.0
 */
public class LoginDelegate implements IApplicationDelegate {
    @Override
    public void onCreate() {
        Log.d("世界是一个bug", "MineDelegate");
        ViewManager.getInstance().addFragment(new LoginFragment());
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }
}
