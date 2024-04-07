package com.example.feature_mine;

import com.example.common.base.IApplicationDelegate;
import com.example.common.base.ViewManager;
import com.example.feature_mine.ui.MineFragment;

/**
 * @Author winiymissl
 * @Date 2024-04-05 13:19
 * @Version 1.0
 */
public class MineDelegate implements IApplicationDelegate {
    @Override
    public void onCreate() {
        /*
         * 添加到ViewManager
         * */
        ViewManager.getInstance().addFragment(MineFragment.newInstance());
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
