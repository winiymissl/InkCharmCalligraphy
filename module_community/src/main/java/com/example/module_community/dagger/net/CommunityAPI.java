package com.example.module_community.dagger.net;

import com.example.module_community.data.model.request.CommunityInfoRequest;
import com.example.module_community.data.model.result.CommunityInfoResult;
import com.example.module_community.data.model.result.PostResult;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:40
 * @Version 1.0
 */
public interface CommunityAPI {

    @POST("/community/look/allposts")
    Single<CommunityInfoResult> getPosts(@Body CommunityInfoRequest request);

    @POST("/community/create/post")
    @Multipart
    Single<PostResult> post(@Header("Authorization") String token, @Part("images") List<RequestBody> images, @Part("content") RequestBody content);
}