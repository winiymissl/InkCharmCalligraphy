package com.example.module_navigation.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.feature_mine.dao.UserInfoDao;
import com.example.feature_mine.dao.model.UserInfo;
import com.example.module_community.dao.CommunityDao;
import com.example.module_community.dao.converter.ConverterImageURI;
import com.example.module_community.dao.entity.CommunityEntity;

/**
 * @Author winiymissl
 * @Date 2024-04-09 22:06
 * @Version 1.0
 */

@Database(entities = {UserInfo.class, CommunityEntity.class}, version = 3, exportSchema = false)
@TypeConverters({ConverterImageURI.class,
})
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserInfoDao UserInfoDao();

    public abstract CommunityDao CommunityInfoDao();
}
