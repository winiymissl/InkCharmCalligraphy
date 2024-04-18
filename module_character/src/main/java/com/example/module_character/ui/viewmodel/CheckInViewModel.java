package com.example.module_character.ui.viewmodel;

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
import com.example.module_character.dagger.CharacterModule;
import com.example.module_character.dagger.DaggerCharacterComponent;
import com.example.module_character.data.Repository;
import com.example.module_character.data.result.CheckInResult;

import java.io.File;

import javax.inject.Inject;

/**
 * @Author winiymissl
 * @Date 2024-04-18 14:33
 * @Version 1.0
 */
public class CheckInViewModel extends BaseViewModel {
    @Inject
    Repository repository;

    private MutableLiveData<CheckInResult> mCheckInMutableLiveData = new MutableLiveData<>();

    private SingleLiveEvent<Throwable> mThrowableLiveData = new SingleLiveEvent<>();

    public LiveData<CheckInResult> getmCheckInMutableLiveData() {
        return mCheckInMutableLiveData;
    }

    public SingleLiveEvent<Throwable> getThrowableLiveData() {
        return mThrowableLiveData;
    }

    public CheckInViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void init() {
        super.init();
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerCharacterComponent.builder().appComponent(appComponent).characterModule(new CharacterModule()).build().injectTo(this);
    }

    @SuppressLint("CheckResult")
    public void checkIn(String token, File file, String content, Integer score) {
        repository.getRemoteDataSource().checkIn(token, file, content, score).subscribe(checkInResult -> {
            Log.d("世界是是一个bug", checkInResult.getMessage());
            mCheckInMutableLiveData.postValue(checkInResult);
        }, error -> {
            Log.d("世界是是一个bug", error.getMessage());
            mThrowableLiveData.postValue(error);
        });
    }
}
