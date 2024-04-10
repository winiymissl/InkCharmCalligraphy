package com.example.feature_mine.ui.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.base.BaseApplication;
import com.example.common.base.BaseViewModel;
import com.example.common.base.MyMMkv;
import com.example.common.dagger.AppComponent;
import com.example.feature_mine.dagger.DaggerMineComponent;
import com.example.feature_mine.dao.model.UserInfo;
import com.example.feature_mine.data.Repository;
import com.example.feature_mine.data.model.UserInfoResult;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class MineViewModel extends BaseViewModel {
    private MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();

    public LiveData<Throwable> getThrowableMutableLiveData() {
        return throwableMutableLiveData;
    }

    @Inject
    Repository repository;

    public MineViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void init() {
        super.init();
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerMineComponent.builder().appComponent(appComponent).build().injectTo(this);
    }

    private MutableLiveData<UserInfoResult> userInfoResult = new MutableLiveData<>();

    public LiveData<UserInfoResult> getUserInfoResult() {
        if (userInfoResult.getValue() == null) {
            /*
             * 第一次申请使用本地数据库的逻辑
             * */
            fetchUserInfoLocalData();
        }
        return userInfoResult;
    }

    @SuppressLint("CheckResult")
    public void fetchUserInfoLocalData() {
        repository.getLocalDataSource().getUserInfoList().subscribe(userInfoList -> {
            if (userInfoList.size() > 0) {
                userInfoResult.setValue(dbInfoConvertToUser(userInfoList.get(0)));
            } else {
                userInfoResult.setValue(null);
            }
        }, error -> {
            throwableMutableLiveData.setValue(error);
            Toast.makeText(getApplication(), "本地数据库申请数据失败", Toast.LENGTH_SHORT).show();
            Log.d("世界是一个bug", "fetchUserInfoLocalData    ：  " + error.toString());
        });
    }

    @SuppressLint("CheckResult")
    public void fetchUserInfoRemoteData(String token) {
        if (token != null) {
            repository.getRemoteDataSource().getUserInfo(token).subscribe(result -> {
                userInfoResult.postValue(result);
                repository.getLocalDataSource().insertUserInfo(userInfoConvertToDb(result)).subscribe(() -> {
                    Log.d("世界是一个bug", "insertUserInfo ： " + "插入成功");
                }, error -> {
                    Log.d("世界是一个bug", "insertUserInfo ： " + error.toString());
                });
            }, error -> {
                throwableMutableLiveData.setValue(error);
                Log.d("世界是一个bug", "fetchUserInfoRemoteData ： " + error.toString());
                Toast.makeText(getApplication(), "网络申请数据失败", Toast.LENGTH_SHORT).show();
            });
        }
    }

    @SuppressLint("CheckResult")
    public void delUserInfo() {
        MyMMkv.getMyDefaultMMkv().remove("token");
        repository.getLocalDataSource().queryUserInfo().subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    repository.getLocalDataSource().deleteUserInfo(result).subscribeOn(Schedulers.io()).subscribe(() -> {
                        Log.d("世界是一个bug", "deleteAllUserInfo ： " + "插入成功");
                        userInfoResult.postValue(null);
                    }, error -> {
                        Log.d("世界是一个bug", "deleteAllUserInfo ： " + error.toString());
                    });
                }, error -> {
                    Log.d("世界是一个bug", "queryUserInfo ： " + error.toString());
                });
    }

    private UserInfoResult dbInfoConvertToUser(UserInfo userInfoList) {
        UserInfoResult result = new UserInfoResult(new UserInfoResult.DataDTO(Integer.valueOf(userInfoList.getId_user()), userInfoList.getNickName(), userInfoList.getAccount(), userInfoList.getEmail(), userInfoList.getAvatarBackground(), userInfoList.getBackgroundImage(), userInfoList.getPhone(), Integer.valueOf(userInfoList.getPostCount()), Integer.valueOf(userInfoList.getFollowCount()), Integer.valueOf(userInfoList.getFansCount()), Integer.valueOf(userInfoList.getLikeCount()), Integer.valueOf(userInfoList.getPointCount())));
        return result;
    }

    private UserInfo userInfoConvertToDb(UserInfoResult userInfo) {
        return new UserInfo(userInfo.getData().getNick_name(), String.valueOf(userInfo.getData().getId()), userInfo.getData().getAccount(), userInfo.getData().getEmail(), userInfo.getData().getAvatar_background(), userInfo.getData().getBackground_image(), userInfo.getData().getPhone(), String.valueOf(userInfo.getData().getPost_count()), String.valueOf(userInfo.getData().getFollow_count()), String.valueOf(userInfo.getData().getFans_count()), String.valueOf(userInfo.getData().getLike_count()), String.valueOf(userInfo.getData().getPoint_count()));
    }
}