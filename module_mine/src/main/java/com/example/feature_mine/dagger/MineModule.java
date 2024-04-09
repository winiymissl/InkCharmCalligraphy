package com.example.feature_mine.dagger;

import com.example.common.dagger.PreActivity;
import com.example.feature_mine.dagger.net.MineAPI;
import com.example.feature_mine.data.LocalDataSource;
import com.example.feature_mine.data.RemoteDataSource;
import com.example.feature_mine.data.Repository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @Author winiymissl
 * @Date 2024-04-05 14:54
 * @Version 1.0
 */
@Module
public class MineModule {
    @Provides
    @PreActivity
    Repository providerRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        return new Repository(remoteDataSource, localDataSource);
    }

    @Provides
    @PreActivity
    MineAPI provideMineAPI(Retrofit retrofit) {
        return retrofit.create(MineAPI.class);
    }
}