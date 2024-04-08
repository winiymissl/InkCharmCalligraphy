package com.example.common.dagger.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author winiymissl
 * @Date 2024-04-04 16:31
 * @Version 1.0
 */
@Module
public class NetModule {
    @Provides
    @Singleton
    Retrofit retrofitProvider() {
        return new Retrofit.Builder().baseUrl(APIService.BASE_URL).addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
