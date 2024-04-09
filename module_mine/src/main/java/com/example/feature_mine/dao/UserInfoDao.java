package com.example.feature_mine.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.feature_mine.dao.model.UserInfo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * @Author winiymissl
 * @Date 2024-04-09 21:40
 * @Version 1.0
 */
@Dao
public interface UserInfoDao {
    @Insert
    Single<Integer> insert(UserInfo user);

    @Delete
    Single<Integer> delete(UserInfo user);

    @Update
    Single<Integer> update(UserInfo user);

    @Query("SELECT * FROM userInfo")
    Single<List<UserInfo>> getAllUsers();
}
