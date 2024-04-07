package com.example.common.dagger;

import com.example.common.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @Author winiymissl
 * @Date 2024-04-04 16:29
 * @Version 1.0
 */
@Module
public class AppModule {
    private final BaseApplication baseApplication;

    public AppModule(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }


    @Provides
    @Singleton
    BaseApplication providesApplication() {
        return baseApplication;
    }
}
