package com.example.module_character.data;

import com.example.common.base.BaseApplication;
import com.example.common.dagger.AppComponent;
import com.example.module_character.dagger.CharacterModule;
import com.example.module_character.dagger.DaggerCharacterComponent;
import com.example.module_character.dagger.net.CharacterAPI;
import com.example.module_character.data.result.CheckInResult;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author winiymissl
 * @Date 2024-04-18 16:29
 * @Version 1.0
 */
public class RemoteDataSource {
    @Inject
    CharacterAPI api;

    @Inject
    public RemoteDataSource(CharacterAPI api) {
        this.api = api;
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerCharacterComponent.builder().appComponent(appComponent).characterModule(new CharacterModule()).build().injectTo(this);
    }

    public Observable<CheckInResult> checkIn(String token, File file, String content, Integer score) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        MultipartBody.Part contentPart = MultipartBody.Part.createFormData("content", content);
        MultipartBody.Part scorePart = MultipartBody.Part.createFormData("score", String.valueOf(score));
        return api.checkIn("Bearer " + token, body, contentPart, scorePart).subscribeOn(Schedulers.io());
    }
}
