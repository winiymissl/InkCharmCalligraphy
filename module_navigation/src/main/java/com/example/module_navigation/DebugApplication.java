package com.example.module_navigation;

import android.app.Application;

import com.example.feature_mine.dao.MineModuleRoomAccessor;
import com.example.feature_mine.dao.UserInfoDao;
import com.example.module_navigation.db.DBHelper;


/**
 * @Author winiymissl
 * @Date 2024-04-08 20:05
 * @Version 1.0
 */
public class DebugApplication extends Application {
    private static DebugApplication application;

    public static DebugApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        MineModuleRoomAccessor.setOnGetDaoCallback(new MineModuleRoomAccessor.OnGetDaoCallback() {
            @Override
            public UserInfoDao onGetUserDao() {
                return DBHelper.getDb().UserInfoDao();
            }
        });
    }
}
