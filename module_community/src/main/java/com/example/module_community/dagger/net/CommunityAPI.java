package com.example.module_community.dagger.net;

import com.example.module_community.data.model.request.CommunityInfoRequest;
import com.example.module_community.data.model.result.CommunityInfoResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:40
 * @Version 1.0
 */
public interface CommunityAPI {

    @POST("/community/look/allposts")
    Observable<CommunityInfoResult> getPosts(@Body CommunityInfoRequest request);

}