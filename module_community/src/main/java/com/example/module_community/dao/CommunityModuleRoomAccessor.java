package com.example.module_community.dao;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:01
 * @Version 1.0
 */
public class CommunityModuleRoomAccessor {
    private static OnGetCommunityDao onGetCommunityDao;


    public static void setOnGetDaoCallback(OnGetCommunityDao onGetDaoCallback) {
        CommunityModuleRoomAccessor.onGetCommunityDao = onGetCommunityDao;
    }

    public static CommunityDao getCommunityDao() {
        if (onGetCommunityDao == null) {
            throw new IllegalArgumentException("onGetDaoCallback must not be null!!");
        }
        return onGetCommunityDao.onGetCommunityDao();
    }

    public interface OnGetCommunityDao {
        CommunityDao onGetCommunityDao();
    }
}
