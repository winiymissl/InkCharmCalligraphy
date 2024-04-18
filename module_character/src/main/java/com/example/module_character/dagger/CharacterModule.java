package com.example.module_character.dagger;

import com.example.common.dagger.PreActivity;
import com.example.module_character.dagger.net.CharacterAPI;
import com.example.module_character.data.RemoteDataSource;
import com.example.module_character.data.Repository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @Author winiymissl
 * @Date 2024-04-17 21:11
 * @Version 1.0
 */

@Module
public class CharacterModule {


    @PreActivity
    @Provides
    Repository provideRepository(RemoteDataSource remoteDataSource) {
        return new Repository(remoteDataSource);
    }

    @PreActivity
    @Provides
    CharacterAPI provideCharacterAPI(Retrofit retrofit) {
        return retrofit.create(CharacterAPI.class);
    }
}
