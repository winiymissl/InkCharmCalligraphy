package com.example.module_community.ui.viewmodel;

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
import com.example.module_community.data.model.result.CancelLikeResult;
import com.example.module_community.data.model.result.CollectResult;
import com.example.module_community.data.model.result.CommentResult;
import com.example.module_community.data.model.result.LikeResult;
import com.example.module_community.data.model.result.PostDetailResult;

import javax.inject.Inject;

/**
 * @Author winiymissl
 * @Date 2024-04-17 13:14
 * @Version 1.0
 */
public class CommunityDetailViewModel extends BaseViewModel {
    @Inject
    Repository repository;
    private SingleLiveEvent<Throwable> mThrowableMutableLiveData = new SingleLiveEvent();
    private MutableLiveData<PostDetailResult> mPostDetailMutableLiveData = new MutableLiveData();

    private MutableLiveData<CommentResult> mCommentMutableLiveData = new MutableLiveData();


    private MutableLiveData<LikeResult> mLikeMutableLiveData = new MutableLiveData();
    private MutableLiveData<CancelLikeResult> mCancelLikeMutableLiveData = new MutableLiveData();
    private MutableLiveData<CollectResult> mCollectMutableLiveData = new MutableLiveData();
    private MutableLiveData<CancelCollectResult> mCancelCollectMutableLiveData = new MutableLiveData();

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

    public void cancelCollect(String token, int post_id) {
        repository.getRemoteDataSource().cancelCollect(token, post_id).subscribe(cancelCollectResult -> {
            mCancelCollectMutableLiveData.postValue(cancelCollectResult);
        }, error -> {
            mThrowableMutableLiveData.postValue(error);
        });
    }

    public void collect(String token, int post_id) {
        repository.getRemoteDataSource().collect(token, post_id).subscribe(collectResult -> {
            mCollectMutableLiveData.postValue(collectResult);
        }, error -> {
            mThrowableMutableLiveData.postValue(error);
        });
    }

    public void cancelLike(String token, int post_id) {
        repository.getRemoteDataSource().cancelLike(token, post_id).subscribe(cancelLikeResult -> {
            mCancelLikeMutableLiveData.postValue(cancelLikeResult);
        }, error -> {
            mThrowableMutableLiveData.postValue(error);
        });
    }

    public void like(String token, int post_id) {
        repository.getRemoteDataSource().like(token, post_id).subscribe(likeResult -> {
            mLikeMutableLiveData.postValue(likeResult);
        }, error -> {
            mThrowableMutableLiveData.postValue(error);
        });
    }

    public void fetchCommentData(int post_id, int page, int page_size) {
        repository.getRemoteDataSource().getComment(post_id, page, page_size).subscribe(commentResult -> {
            mCommentMutableLiveData.postValue(commentResult);
        }, error -> {
            mThrowableMutableLiveData.postValue(error);
        });
    }

    public void fetchPostData(String token, Integer id) {
        Log.d("世界是一个bug", token.toString());
        repository.getRemoteDataSource().getPostDetail(token, id).subscribe(postDetailResult -> {
            Log.d("世界是一个bug", postDetailResult.toString());
            mPostDetailMutableLiveData.postValue(postDetailResult);
        }, error -> {
            Log.d("世界是一个bug", error.toString());
            mThrowableMutableLiveData.postValue(error);
        });
    }
}
