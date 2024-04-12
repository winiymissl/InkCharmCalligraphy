package com.example.module_community.data;

import com.example.common.base.BaseApplication;
import com.example.common.dagger.AppComponent;
import com.example.module_community.dagger.CommunityModule;
import com.example.module_community.dagger.DaggerCommunityComponent;
import com.example.module_community.dagger.net.CommunityAPI;

import javax.inject.Inject;

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
        DaggerCommunityComponent.builder().appComponent(appComponent).communityModule(new CommunityModule()).build();
    }
}
