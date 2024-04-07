package com.example.common.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.dagger.AppComponent;

/**
 * @Author winiymissl
 * @Date 2024-04-04 18:11
 * @Version 1.0
 */
public class BaseViewModel extends AndroidViewModel {
    /*
     * 有一个报错的MutableLiveData
     * */
    protected MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    public LiveData<Throwable> getThrowableMutableLiveData() {
        return throwableMutableLiveData;
    }

    protected AppComponent getAppComponent() {
        return BaseApplication.getAppComponent();
    }

    protected void init() {

    }
}
