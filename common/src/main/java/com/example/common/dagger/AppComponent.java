package com.example.common.dagger;

import com.example.common.base.BaseApplication;
import com.example.common.dagger.network.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * @Author winiymissl
 * @Date 2024-04-04 16:31
 * @Version 1.0
 */
@Singleton
@Component(modules = {NetModule.class, AppModule.class})
public interface AppComponent {
    Retrofit provideRetrofit();

    BaseApplication providesApplication();
}
