package com.example.module_character.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.common.base.BaseApplication;
import com.example.common.base.BaseViewModel;
import com.example.common.dagger.AppComponent;
import com.example.common.livedata.SingleLiveEvent;
import com.example.module_character.dagger.CharacterModule;
import com.example.module_character.dagger.DaggerCharacterComponent;
import com.example.module_character.data.Repository;
import com.example.module_character.data.result.SentenceResult;

import javax.inject.Inject;

/**
 * @Author winiymissl
 * @Date 2024-04-21 14:54
 * @Version 1.0
 */
public class CharacterViewModel extends BaseViewModel {
    @Inject
    Repository repository;

    private SingleLiveEvent<SentenceResult> mSentenceMutableLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<Throwable> mThrowableLiveData = new SingleLiveEvent<>();

    public LiveData<SentenceResult> getmSentenceMutableLiveData() {
        return mSentenceMutableLiveData;
    }

    public SingleLiveEvent<Throwable> getThrowableLiveData() {
        return mThrowableLiveData;
    }

    @Override
    protected void init() {
        super.init();
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerCharacterComponent.builder().appComponent(appComponent).characterModule(new CharacterModule()).build().injectTo(this);
    }

    public CharacterViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchSentence(String c) {
        repository.getRemoteDataSource().getSentence(c).subscribe(sentenceResult -> {
            Log.d("世界是一个bug", sentenceResult.toString());
            mSentenceMutableLiveData.postValue(sentenceResult);
        }, error -> {
            mThrowableLiveData.postValue(error);
        });
    }
}
