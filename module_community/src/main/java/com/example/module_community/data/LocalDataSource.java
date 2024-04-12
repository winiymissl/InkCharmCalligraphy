package com.example.module_community.data;

import com.example.module_community.dao.CommunityModuleRoomAccessor;
import com.example.module_community.dao.entity.CommunityEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author winiymissl
 * @Date 2024-04-12 0:31
 * @Version 1.0
 */
public class LocalDataSource {

    @Inject
    public LocalDataSource() {
    }

    public Completable insertCommunityInfo(CommunityEntity entity) {
        return CommunityModuleRoomAccessor.getCommunityDao().insertCommunityInfo(entity).observeOn(Schedulers.io());
    }

    public Completable updateCommunityInfo(CommunityEntity entity) {
        return CommunityModuleRoomAccessor.getCommunityDao().updateCommunityInfo(entity).observeOn(Schedulers.io());
    }

    public Completable deleteCommunityInfo(List<CommunityEntity> entity) {
        return CommunityModuleRoomAccessor.getCommunityDao().deleteCommunityInfo(entity).observeOn(Schedulers.io());
    }

    public Single<List<CommunityEntity>> getCommunityInfo() {
        return CommunityModuleRoomAccessor.getCommunityDao().getCommunityInfoAll().observeOn(Schedulers.io());
    }
}