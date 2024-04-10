package com.example.feature_mine.data;

import com.example.common.base.BaseApplication;
import com.example.common.dagger.AppComponent;
import com.example.feature_mine.dagger.DaggerMineComponent;
import com.example.feature_mine.dagger.net.MineAPI;
import com.example.feature_mine.data.model.UserInfoResult;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author winiymissl
 * @Date 2024-04-05 14:52
 * @Version 1.0
 */
public class RemoteDataSource {
    @Inject
    MineAPI api;

    @Inject
    public RemoteDataSource(MineAPI loginAPI) {
        this.api = loginAPI;
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerMineComponent.builder().appComponent(appComponent).build().injectTo(this);
    }

    public Observable<UserInfoResult> getUserInfo(String token) {
        return api.getUserinfo("Bearer " + token).subscribeOn(Schedulers.io());
    }
}
