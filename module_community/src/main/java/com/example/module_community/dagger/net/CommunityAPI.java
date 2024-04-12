package com.example.module_community.dagger.net;

import com.example.module_community.data.model.CommunityInfoResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:40
 * @Version 1.0
 */
public interface CommunityAPI {

    @GET("/look/allposts")
    Observable<CommunityInfoResult> getPosts();

}