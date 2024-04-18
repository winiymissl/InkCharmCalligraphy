package com.example.feature_mine.dagger.net;

import com.example.feature_mine.data.model.request.ChangeUserInfoRequest;
import com.example.feature_mine.data.model.request.FansRequest;
import com.example.feature_mine.data.model.request.FollowRequest;
import com.example.feature_mine.data.model.result.ChangeUserInfoResult;
import com.example.feature_mine.data.model.result.FansResult;
import com.example.feature_mine.data.model.result.FollowResult;
import com.example.feature_mine.data.model.result.UserInfoResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @Author winiymissl
 * @Date 2024-04-05 14:52
 * @Version 1.0
 */
public interface MineAPI {
    @POST("/user/userinfo")
    Observable<UserInfoResult> getUserinfo(@Header("Authorization") String token);

    @POST("/user/modinfo")
    Observable<ChangeUserInfoResult> changeUserInfo(@Header("Authorization") String token, @Body ChangeUserInfoRequest request);

    @POST("/user/look/all_fans")
    Observable<FansResult> getFans(@Header("Authorization") String token, @Body FansRequest request);

    @POST("/user/look/all_follow")
    Observable<FollowResult> getFollow(@Header("Authorization") String token, @Body FollowRequest request);
}
