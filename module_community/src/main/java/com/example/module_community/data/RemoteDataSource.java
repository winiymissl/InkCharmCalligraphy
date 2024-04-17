package com.example.module_community.data;

import com.example.common.base.BaseApplication;
import com.example.common.dagger.AppComponent;
import com.example.module_community.dagger.CommunityModule;
import com.example.module_community.dagger.DaggerCommunityComponent;
import com.example.module_community.dagger.net.CommunityAPI;
import com.example.module_community.data.model.request.CancelCollectRequest;
import com.example.module_community.data.model.request.CancelLikeRequest;
import com.example.module_community.data.model.request.CollectRequest;
import com.example.module_community.data.model.request.CommentRequest;
import com.example.module_community.data.model.request.CommunityInfoRequest;
import com.example.module_community.data.model.request.LikeRequest;
import com.example.module_community.data.model.request.PostDetailRequest;
import com.example.module_community.data.model.result.CancelCollectResult;
import com.example.module_community.data.model.result.CancelLikeResult;
import com.example.module_community.data.model.result.CollectResult;
import com.example.module_community.data.model.result.CommentResult;
import com.example.module_community.data.model.result.CommunityInfoResult;
import com.example.module_community.data.model.result.LikeResult;
import com.example.module_community.data.model.result.PostDetailResult;
import com.example.module_community.data.model.result.PostResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:33
 * @Version 1.0
 */
public class RemoteDataSource {
    @Inject
    CommunityAPI api;

    @Inject
    public RemoteDataSource(CommunityAPI api) {
        this.api = api;
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerCommunityComponent.builder().appComponent(appComponent).communityModule(new CommunityModule()).build().injectTo(this);
    }

    public Observable<CommunityInfoResult> getAllPosts(int page, int pageSize) {
        return api.getPosts(new CommunityInfoRequest(page, pageSize)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<PostResult> createPost(String token, List<File> list, String content) {
        /*
         * 处理有关逻辑
         * */
        List<RequestBody> requestBodyList = new ArrayList<>();
        list.forEach(file -> {
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            requestBodyList.add(body);
        });
        RequestBody contentBody = RequestBody.create(MediaType.parse("multipart/form-data"), content);
        return api.post(token, requestBodyList, contentBody).subscribeOn(Schedulers.io());
    }

    public Observable<PostDetailResult> getPostDetail(String token, int post_id) {
        return api.getPostDetail("Bearer " + token, new PostDetailRequest(post_id)).subscribeOn(Schedulers.io());
    }

    public Observable<CommentResult> getComment(int post_id, int page, int page_size) {
        return api.getComment(new CommentRequest(post_id, page, page_size)).subscribeOn(Schedulers.io());
    }

    public Observable<LikeResult> like(String token, int post_id) {
        return api.like(token, new LikeRequest(post_id)).subscribeOn(Schedulers.io());
    }

    public Observable<CancelLikeResult> cancelLike(String token, int post_id) {
        return api.cancelLike(token, new CancelLikeRequest(post_id)).subscribeOn(Schedulers.io());
    }

    public Observable<CollectResult> collect(String token, int post_id) {
        return api.collect(token, new CollectRequest(post_id)).subscribeOn(Schedulers.io());
    }

    public Observable<CancelCollectResult> cancelCollect(String token, int post_id) {
        return api.cancelCollect(token, new CancelCollectRequest(post_id)).subscribeOn(Schedulers.io());
    }
}
