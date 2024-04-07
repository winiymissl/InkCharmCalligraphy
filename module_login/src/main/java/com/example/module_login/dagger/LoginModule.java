package com.example.module_login.dagger;

import com.example.common.dagger.PreActivity;
import com.example.module_login.dagger.net.LoginAPI;
import com.example.module_login.data.RemoteDataSource;
import com.example.module_login.data.Repository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @Author winiymissl
 * @Date 2024-04-04 16:49
 * @Version 1.0
 */
@Module
public class LoginModule {

    @Provides
    @PreActivity
    public LoginAPI providerLoginAPI(Retrofit retrofit) {
        return retrofit.create(LoginAPI.class);
    }


    @Provides
    @PreActivity
    public Repository providerRepository(RemoteDataSource remoteDataSource) {
        return new Repository(remoteDataSource);
    }
}
