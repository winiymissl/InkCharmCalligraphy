package com.example.feature_mine.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.base.BaseViewModel;
import com.example.feature_mine.data.Repository;
import com.example.feature_mine.data.model.UserInfoResult;

import javax.inject.Inject;

public class MineViewModel extends BaseViewModel {

    @Inject
    Repository repository;

    public MineViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void init() {
        super.init();
        
    }

    private MutableLiveData<UserInfoResult> userInfoResult = new MutableLiveData<>();

    public LiveData<UserInfoResult> getUserInfoResult() {
        if (userInfoResult.getValue() == null) {

        }
        return userInfoResult;
    }

    private void fetchUserInfo() {

    }
}