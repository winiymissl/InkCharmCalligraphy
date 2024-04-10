package com.example.feature_mine.dagger.net;

import com.example.feature_mine.data.model.UserInfoResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * @Author winiymissl
 * @Date 2024-04-05 14:52
 * @Version 1.0
 */
public interface MineAPI {
    @GET("/user/userinfo")
    Observable<UserInfoResult> getUserinfo(@Header("Authorization") String token);
}
