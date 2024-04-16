package com.example.module_community.data;

import com.example.common.base.BaseApplication;
import com.example.common.dagger.AppComponent;
import com.example.module_community.dagger.CommunityModule;
import com.example.module_community.dagger.DaggerCommunityComponent;
import com.example.module_community.dagger.net.CommunityAPI;
import com.example.module_community.data.model.request.CommunityInfoRequest;
import com.example.module_community.data.model.result.CommunityInfoResult;
import com.example.module_community.data.model.result.PostResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
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

    public Single<CommunityInfoResult> getAllPosts(int page, int pageSize) {
        return api.getPosts(new CommunityInfoRequest(page, pageSize)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<PostResult> createPost(String token, List<File> list, String content) {
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
}
