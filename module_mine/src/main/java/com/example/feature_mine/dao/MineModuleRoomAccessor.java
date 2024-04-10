package com.example.feature_mine.dao;

/**
 * @Author winiymissl
 * @Date 2024-04-09 21:58
 * @Version 1.0
 */
public class MineModuleRoomAccessor {
    private static OnGetDaoCallback onGetDaoCallback;


    public static void setOnGetDaoCallback(OnGetDaoCallback onGetDaoCallback) {
        MineModuleRoomAccessor.onGetDaoCallback = onGetDaoCallback;
    }

    public static UserInfoDao getUserDao() {
        if (onGetDaoCallback == null) {
            throw new IllegalArgumentException("onGetDaoCallback must not be null!!");
        }
        return onGetDaoCallback.onGetUserDao();
    }

    public interface OnGetDaoCallback {
        UserInfoDao onGetUserDao();
    }
}
