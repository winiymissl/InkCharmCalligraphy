package com.example.module_community.ui.viewmodel;

import static com.example.module_community.ui.CommunityFragment.LOADING_FIL;

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
import com.example.module_community.data.model.result.CommunityInfoResult;
import com.example.module_community.data.model.result.PostResult;

import java.io.File;
import java.util.List;

import javax.inject.Inject;


public class CommunityViewModel extends BaseViewModel {
    @Inject
    Repository repository;
    /*
     * 事件性通知
     * */
    private SingleLiveEvent<Throwable> throwableMutableLiveData = new SingleLiveEvent<>();
    private MutableLiveData<CommunityInfoResult> communityInfoResultMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<PostResult> postResultMutableLiveData = new MutableLiveData<>();

//    public Pager<Integer, CommunityItem> pager;
//    public Flowable<PagingData<CommunityItem>> flowable;

    public CommunityViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void init() {
        super.init();
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerCommunityComponent.builder().appComponent(appComponent).communityModule(new CommunityModule()).build().injectTo(this);
//        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
//        pager = new Pager<>(new PagingConfig(10), MyRxPagingSource::new);
//        flowable = PagingRx.getFlowable(pager);
//        PagingRx.cachedIn(flowable, viewModelScope);
    }

    public LiveData<CommunityInfoResult> getListMutableLiveData() {
        return communityInfoResultMutableLiveData;
    }

    public SingleLiveEvent<Throwable> getThrowableMutableLiveData() {
        return throwableMutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void fetchRemoteDataSource(int page, int pageSize) {
        /*
         * 只能在主线程？
         * */
        repository.getRemoteDataSource().getAllPosts(page, pageSize).subscribe(result -> {
            Log.d("世界是一个bug", result.toString());
            if (result.getData().getPost_data() == null) {
                throwableMutableLiveData.setValue(new Throwable(LOADING_FIL));
            } else {
                communityInfoResultMutableLiveData.setValue(result);
            }
        }, error -> {
            Log.d("世界是一个bug", error.toString());
            throwableMutableLiveData.setValue(error);
        });
    }

    @SuppressLint("CheckResult")
    public void postData(String token, List<File> list, String content) {
        repository.getRemoteDataSource().createPost(token, list, content).subscribe(result -> {
            postResultMutableLiveData.postValue(result);
        }, error -> {
            Log.d("世界是一个bug", error.toString());
            throwableMutableLiveData.postValue(error);
        });
    }
}