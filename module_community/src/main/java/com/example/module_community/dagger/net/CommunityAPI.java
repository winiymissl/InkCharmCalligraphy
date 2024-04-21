package com.example.module_community.dagger.net;

import com.example.module_community.data.model.request.CancelCollectRequest;
import com.example.module_community.data.model.request.CancelFollowRequest;
import com.example.module_community.data.model.request.CancelLikeRequest;
import com.example.module_community.data.model.request.CollectRequest;
import com.example.module_community.data.model.request.CommentRequest;
import com.example.module_community.data.model.request.CommunityInfoRequest;
import com.example.module_community.data.model.request.FollowRequest;
import com.example.module_community.data.model.request.LikeRequest;
import com.example.module_community.data.model.request.PostDetailRequest;
import com.example.module_community.data.model.result.CancelCollectResult;
import com.example.module_community.data.model.result.CancelFollowResult;
import com.example.module_community.data.model.result.CancelLikeResult;
import com.example.module_community.data.model.result.CollectResult;
import com.example.module_community.data.model.result.CommentResult;
import com.example.module_community.data.model.result.CommunityInfoResult;
import com.example.module_community.data.model.result.FollowResult;
import com.example.module_community.data.model.result.LikeResult;
import com.example.module_community.data.model.result.PostDetailResult;
import com.example.module_community.data.model.result.PostResult;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
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
    Observable<CommunityInfoResult> getPosts(@Body CommunityInfoRequest request);

    @POST("/community/create/post")
    @Multipart
    Observable<PostResult> post(@Header("Authorization") String token, @Part List<MultipartBody.Part> images, @Part MultipartBody.Part content);

    @POST("/community/view/post_details")
    Observable<PostDetailResult> getPostDetail(@Header("Authorization") String token, @Body PostDetailRequest request);

    @POST("/community/look/comment")
    Observable<CommentResult> getComment(@Body CommentRequest request);

    @POST("/community/like/post")
    Observable<LikeResult> like(@Header("Authorization") String token, @Body LikeRequest request);

    @POST("/community/cancel/like")
    Observable<CancelLikeResult> cancelLike(@Header("Authorization") String token, @Body CancelLikeRequest request);

    @POST("/community/collect/post")
    Observable<CollectResult> collect(@Header("Authorization") String token, @Body CollectRequest request);

    @POST("/community/cancel/collect")
    Observable<CancelCollectResult> cancelCollect(@Header("Authorization") String token, @Body CancelCollectRequest request);

    @POST("/user/follow")
    Observable<FollowResult> follow(@Header("Authorization") String token, @Body FollowRequest request);

    @POST("/user/cancel_follow")
    Observable<CancelFollowResult> cancelFollow(@Header("Authorization") String token, @Body CancelFollowRequest request);
}