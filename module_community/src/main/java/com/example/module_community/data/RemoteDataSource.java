package com.example.module_community.data;

import com.example.common.base.BaseApplication;
import com.example.common.dagger.AppComponent;
import com.example.module_community.dagger.CommunityModule;
import com.example.module_community.dagger.DaggerCommunityComponent;
import com.example.module_community.dagger.net.CommunityAPI;
import com.example.module_community.data.model.request.CommunityInfoRequest;
import com.example.module_community.data.model.result.CommunityInfoResult;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:33
 * @Version 1.0
 */
public class RemoteDataSource {
    @Inject
    CommunityAPI api;

    @Inject
    public RemoteDataSource(CommunityAPI api) {
        this.api = api;
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerCommunityComponent.builder().appComponent(appComponent).communityModule(new CommunityModule()).build().injectTo(this);
    }

    public Observable<CommunityInfoResult> getAllPosts(int page, int pageSize) {
        return api.getPosts(new CommunityInfoRequest(page, pageSize)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
