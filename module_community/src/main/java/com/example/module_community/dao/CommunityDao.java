package com.example.module_community.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.module_community.dao.entity.CommunityEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/**
 * @Author winiymissl
 * @Date 2024-04-12 0:35
 * @Version 1.0
 */
@Dao
public interface CommunityDao {
    @Insert
    Completable insertCommunityInfo(CommunityEntity entity);

    @Delete
    Completable deleteCommunityInfo(List<CommunityEntity> entity);

    @Query("SELECT * FROM community_info")
    Single<List<CommunityEntity>> getCommunityInfoAll();

    @Update
    Completable updateCommunityInfo(CommunityEntity entity);
}
