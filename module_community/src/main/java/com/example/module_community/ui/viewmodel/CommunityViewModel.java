package com.example.module_community.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.base.BaseApplication;
import com.example.common.base.BaseViewModel;
import com.example.common.dagger.AppComponent;
import com.example.module_community.dagger.CommunityModule;
import com.example.module_community.dagger.DaggerCommunityComponent;
import com.example.module_community.dao.Repository;
import com.example.module_community.dao.entity.CommunityEntity;
import com.example.module_community.data.model.CommunityInfoResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class CommunityViewModel extends BaseViewModel {
    @Inject
    Repository repository;
    private MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CommunityInfoResult> listMutableLiveData = new MutableLiveData<>();

    public LiveData<CommunityInfoResult> getListMutableLiveData() {
        if (listMutableLiveData.getValue() == null) {
            /*
             * 优先从本地获取数据
             * */
            fetchLocalDataSource();
        }
        return listMutableLiveData;
    }

    public LiveData<Throwable> getThrowableMutableLiveData() {
        return throwableMutableLiveData;
    }

    @Override
    protected void init() {
        super.init();
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerCommunityComponent.builder().appComponent(appComponent).communityModule(new CommunityModule()).build();
    }

    public CommunityViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchLocalDataSource() {
        repository.getLocalDataSource().getCommunityInfo().subscribe(entities -> {
            listMutableLiveData.postValue(db2Community(entities));
        }, error -> {
            throwableMutableLiveData.postValue(error);
        });
    }

    public void fetchRemoteDataSource() {

    }

    private CommunityInfoResult db2Community(List<CommunityEntity> list) {
        List<CommunityInfoResult.DataDTO.PostDataDTO> postDataDTOList = new ArrayList<>();
        list.forEach(entity -> {
            postDataDTOList.add(new CommunityInfoResult.DataDTO.PostDataDTO(
                    entity.getId_post(),
                    entity.getUserId(),
                    entity.getContentCount(),
                    entity.getLikeCount(),
                    entity.getCollectCount(),
                    entity.getContent(),
                    entity.getCreateTime(),
                    entity.getDeleteTime(),
                    entity.getImageUrls()));
        });
        return new CommunityInfoResult(new CommunityInfoResult.DataDTO(postDataDTOList));
    }


}