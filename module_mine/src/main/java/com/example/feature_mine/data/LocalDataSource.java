package com.example.feature_mine.data;

import com.example.feature_mine.dao.MineModuleRoomAccessor;
import com.example.feature_mine.dao.UserInfoDao;
import com.example.feature_mine.dao.model.UserInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author winiymissl
 * @Date 2024-04-05 14:52
 * @Version 1.0
 */
public class LocalDataSource {

    @Inject
    public LocalDataSource() {
    }

    public Single<List<UserInfo>> getUserInfoList() {
        return MineModuleRoomAccessor.getUserDao().getAllUsers().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    public Completable insertUserInfo(UserInfo userInfo) {
        return MineModuleRoomAccessor.getUserDao().insert(userInfo);
    }

    public Completable deleteUserInfo(List<UserInfo> userInfo) {
        return MineModuleRoomAccessor.getUserDao().delete(userInfo);
    }

    public Single<List<UserInfo>> queryUserInfo() {
        return MineModuleRoomAccessor.getUserDao().getAllUsers();
    }
}