package com.example.feature_mine.dagger.net;

import com.example.feature_mine.data.model.request.ChangeUserInfoRequest;
import com.example.feature_mine.data.model.result.ChangeUserInfoResult;
import com.example.feature_mine.data.model.result.UserInfoResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @Author winiymissl
 * @Date 2024-04-05 14:52
 * @Version 1.0
 */
public interface MineAPI {
    @GET("/user/userinfo")
    Observable<UserInfoResult> getUserinfo(@Header("Authorization") String token);

    @POST("/user/modinfo")
    Observable<ChangeUserInfoResult> changeUserInfo(@Header("Authorization") String token, @Body ChangeUserInfoRequest request);
}
