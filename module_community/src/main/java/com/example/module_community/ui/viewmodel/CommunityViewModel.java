package com.example.module_community.ui.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.base.BaseApplication;
import com.example.common.base.BaseViewModel;
import com.example.common.dagger.AppComponent;
import com.example.module_community.dagger.CommunityModule;
import com.example.module_community.dagger.DaggerCommunityComponent;
import com.example.module_community.data.Repository;
import com.example.module_community.data.model.result.CommunityInfoResult;

import javax.inject.Inject;


public class CommunityViewModel extends BaseViewModel {
    @Inject
    Repository repository;
    private MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CommunityInfoResult> communityInfoResultMutableLiveData = new MutableLiveData<>();

    public CommunityViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void init() {
        super.init();
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerCommunityComponent.builder().appComponent(appComponent).communityModule(new CommunityModule()).build().injectTo(this);
    }

    public LiveData<CommunityInfoResult> getListMutableLiveData() {
        return communityInfoResultMutableLiveData;
    }

    public LiveData<Throwable> getThrowableMutableLiveData() {

        return throwableMutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void fetchRemoteDataSource(int page, int pageSize) {
        /*
         * 只能在主线程？
         * */
        repository.getRemoteDataSource().getAllPosts(page, pageSize).subscribe(result -> {
                    communityInfoResultMutableLiveData.setValue(result);
                },
                error -> {
                    throwableMutableLiveData.setValue(error);
                });
    }
}