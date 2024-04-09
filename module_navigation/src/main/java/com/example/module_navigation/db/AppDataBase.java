package com.example.module_navigation.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.feature_mine.dao.UserInfoDao;
import com.example.feature_mine.dao.model.UserInfo;

/**
 * @Author winiymissl
 * @Date 2024-04-09 22:06
 * @Version 1.0
 */
@Database(entities = {UserInfo.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserInfoDao UserInfoDao();
}
