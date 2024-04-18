package com.example.module_character.dagger.net;

import com.example.module_character.data.result.CheckInResult;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @Author winiymissl
 * @Date 2024-04-17 21:12
 * @Version 1.0
 */
public interface CharacterAPI {
    @POST("/grow/check/in")
    @Multipart
    Observable<CheckInResult> checkIn(@Header("Authorization") String token, @Part MultipartBody.Part request, @Part MultipartBody.Part content, @Part MultipartBody.Part score);
}