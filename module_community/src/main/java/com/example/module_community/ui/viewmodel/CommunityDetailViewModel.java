package com.example.module_community.ui.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.base.BaseApplication;
import com.example.common.base.BaseViewModel;
import com.example.common.dagger.AppComponent;
import com.example.common.livedata.SingleLiveEvent;
import com.example.module_community.dagger.CommunityModule;
import com.example.module_community.dagger.DaggerCommunityComponent;
import com.example.module_community.data.Repository;
import com.example.module_community.data.model.result.CancelCollectResult;
import com.example.module_community.data.model.result.CancelFollowResult;
import com.example.module_community.data.model.result.CancelLikeResult;
import com.example.module_community.data.model.result.CollectResult;
import com.example.module_community.data.model.result.CommentResult;
import com.example.module_community.data.model.result.FollowResult;
import com.example.module_community.data.model.result.LikeResult;
import com.example.module_community.data.model.result.PostDetailResult;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author winiymissl
 * @Date 2024-04-17 13:14
 * @Version 1.0
 */
public class CommunityDetailViewModel extends BaseViewModel {
    @Inject
    Repository repository;
    private SingleLiveEvent<Throwable> mThrowableMutableLiveData = new SingleLiveEvent();
    private MutableLiveData<PostDetailResult> mPostDetailMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CommentResult> mCommentMutableLiveData = new MutableLiveData<>();
    private SingleLiveEvent<LikeResult> mLikeMutableLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<CancelLikeResult> mCancelLikeMutableLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<CollectResult> mCollectMutableLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<CancelCollectResult> mCancelCollectMutableLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<FollowResult> mFollowMutableLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<CancelFollowResult> mCancelFollowMutableLiveData = new SingleLiveEvent<>();

    private int user_id;

    private int post_id;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LiveData<FollowResult> getmFollowMutableLiveData() {
        return mFollowMutableLiveData;
    }

    public LiveData<CancelFollowResult> getmCancelFollowMutableLiveData() {
        return mCancelFollowMutableLiveData;
    }

    public LiveData<CancelCollectResult> getmCancelCollectMutableLiveData() {
        return mCancelCollectMutableLiveData;
    }

    public LiveData<CollectResult> getmCollectMutableLiveData() {
        return mCollectMutableLiveData;
    }

    public LiveData<CancelLikeResult> getmCancelLikeMutableLiveData() {
        return mCancelLikeMutableLiveData;
    }

    public LiveData<LikeResult> getmLikeMutableLiveData() {
        return mLikeMutableLiveData;
    }

    public LiveData<CommentResult> getmCommentMutableLiveData() {
        return mCommentMutableLiveData;
    }

    public SingleLiveEvent<Throwable> getThrowableMutableLiveData() {
        return mThrowableMutableLiveData;
    }

    public LiveData<PostDetailResult> getmPostDetailMutableLiveData() {
        return mPostDetailMutableLiveData;
    }

    public CommunityDetailViewModel(@NonNull Application application) {
        super(application);

    }

    @Override
    protected void init() {
        super.init();
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerCommunityComponent.builder().appComponent(appComponent).communityModule(new CommunityModule()).build().injectTo(this);
    }

    @SuppressLint("CheckResult")
    public void cancelFollow(String token, int user_id) {
        repository.getRemoteDataSource().cancelFollow(token, user_id).subscribeOn(Schedulers.io()).subscribe(cancelFollowResult -> {
            mCancelFollowMutableLiveData.postValue(cancelFollowResult);
        }, error -> {
            Log.d("世界是一个bug", "cancelFollow  :  " + error.toString());
            mThrowableMutableLiveData.postValue(error);
        });
    }
    @SuppressLint("CheckResult")
    public void follow(String token, int user_id) {
        repository.getRemoteDataSource().follow(token, user_id).subscribe(followResult -> {
            mFollowMutableLiveData.postValue(followResult);
        }, error -> {
            Log.d("世界是一个bug", "follow  :  " + error.toString());

            mThrowableMutableLiveData.postValue(error);
        });
    }

    @SuppressLint("CheckResult")
    public void cancelCollect(String token, int post_id) {
        repository.getRemoteDataSource().cancelCollect(token, post_id).subscribe(cancelCollectResult -> {
            mCancelCollectMutableLiveData.postValue(cancelCollectResult);
        }, error -> {
            Log.d("世界是一个bug", "cancelCollect  :  " + error.toString());
            mThrowableMutableLiveData.postValue(error);
        });
    }

    @SuppressLint("CheckResult")
    public void collect(String token, int post_id) {
        repository.getRemoteDataSource().collect(token, post_id).subscribe(collectResult -> {
            mCollectMutableLiveData.postValue(collectResult);
        }, error -> {
            Log.d("世界是一个bug", "collect  :  " + error.toString());
            mThrowableMutableLiveData.postValue(error);
        });
    }

    @SuppressLint("CheckResult")
    public void cancelLike(String token, int post_id) {
        repository.getRemoteDataSource().cancelLike(token, post_id).subscribe(cancelLikeResult -> {
            mCancelLikeMutableLiveData.postValue(cancelLikeResult);
        }, error -> {
            Log.d("世界是一个bug", "cancelLike  :  " + error.toString());
            mThrowableMutableLiveData.postValue(error);
        });
    }

    @SuppressLint("CheckResult")
    public void like(String token, int post_id) {
        repository.getRemoteDataSource().like(token, post_id).subscribe(likeResult -> {
            mLikeMutableLiveData.postValue(likeResult);
        }, error -> {
            Log.d("世界是一个bug", "like  :  " + error.toString());
            mThrowableMutableLiveData.postValue(error);
        });
    }

    @SuppressLint("CheckResult")
    public void fetchCommentData(int post_id, int page, int page_size) {
        repository.getRemoteDataSource().getComment(post_id, page, page_size).subscribe(commentResult -> {
            mCommentMutableLiveData.postValue(commentResult);
        }, error -> {
            Log.d("世界是一个bug", "getComment  :  " + error.toString());
            mThrowableMutableLiveData.postValue(error);
        });
    }

    @SuppressLint("CheckResult")
    public void fetchPostData(String token, Integer id) {
        Log.d("世界是一个bug", token.toString());
        repository.getRemoteDataSource().getPostDetail(token, id).subscribe(postDetailResult -> {
            Log.d("世界是一个bug", postDetailResult.toString());
            mPostDetailMutableLiveData.postValue(postDetailResult);
        }, error -> {
            Log.d("世界是一个bug", "getPostDetail  :  " + error.toString());
            mThrowableMutableLiveData.postValue(error);
        });
    }
}
