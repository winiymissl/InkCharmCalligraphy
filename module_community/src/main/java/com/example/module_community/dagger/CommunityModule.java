package com.example.module_community.dagger;

import com.example.common.dagger.PreActivity;
import com.example.module_community.dagger.net.CommunityAPI;
import com.example.module_community.data.RemoteDataSource;
import com.example.module_community.data.Repository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:37
 * @Version 1.0
 */
@Module
public class CommunityModule {
    @PreActivity
    @Provides
    Repository provideRepository(RemoteDataSource remoteDataSource) {
        return new Repository(remoteDataSource);
    }

    @PreActivity
    @Provides
    CommunityAPI provideCommunityAPI(Retrofit retrofit) {
        return retrofit.create(CommunityAPI.class);
    }
}
