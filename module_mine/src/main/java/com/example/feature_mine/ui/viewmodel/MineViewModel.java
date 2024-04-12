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
import com.example.feature_mine.data.model.result.ChangeUserInfoResult;
import com.example.feature_mine.data.model.result.UserInfoResult;
import com.example.feature_mine.ui.formstate.InputFormState;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class MineViewModel extends BaseViewModel {
    private MutableLiveData<UserInfoResult> userInfoResult = new MutableLiveData<>();
    private MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<ChangeUserInfoResult> changeUserInfoResultMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<InputFormState> inputFormState = new MutableLiveData<>();

    public LiveData<Throwable> getThrowableMutableLiveData() {
        return throwableMutableLiveData;
    }

    public LiveData<ChangeUserInfoResult> getChangeUserInfoResultMutableLiveData() {
        return changeUserInfoResultMutableLiveData;
    }

    public LiveData<InputFormState> getInputFormState() {
        return inputFormState;
    }

    public void dataChange(String nickName) {
        if (isNickNameValid(nickName)) {
            inputFormState.setValue(new InputFormState("昵称不可用", false));
        } else {
            inputFormState.setValue(new InputFormState(true));
        }
    }

    private boolean isNickNameValid(String nickName) {
        String regex = "\\s";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 创建 Matcher 对象，并在输入字符串上执行匹配
        Matcher matcher = pattern.matcher(nickName);
        return matcher.find();
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


    public LiveData<UserInfoResult> getUserInfoResult() {
        if (userInfoResult.getValue() == null) {
            /*
             * 第一次申请使用本地数据库的逻辑
             * */
            fetchUserInfoLocalData();
        }
        return userInfoResult;
    }

    public void changeUserInfo(String token, String name, String phone) {
        if (token != null) {
            repository.getRemoteDataSource().changeUserInfo(token, name, phone)
                    .subscribe(changeUserInfoResult -> {
                        changeUserInfoResultMutableLiveData.postValue(changeUserInfoResult);
                    }, error -> {
                        throwableMutableLiveData.setValue(error);
                    });
        }
    }

    public void changeUserInfoRemote(String token, String name) {
        if (token != null) {
            repository.getRemoteDataSource().changeUserInfo(token, name)
                    .subscribe(changeUserInfoResult -> {
                        changeUserInfoResultMutableLiveData.postValue(changeUserInfoResult);
                    }, error -> {
                        throwableMutableLiveData.setValue(error);
                    });
        }
    }

    public void updateUserInfoLocal(String nickName, String phone) {
        if (nickName != null) {
            repository.getLocalDataSource().queryUserInfo(nickName)
                    .subscribeOn(Schedulers.io())
                    .subscribe(userInfo -> {
                        repository.getLocalDataSource().updateUserInfo(new UserInfo(
                                userInfo.getId_user(),
                                nickName,
                                userInfo.getAccount(),
                                userInfo.getEmail(),
                                userInfo.getAvatarBackground(),
                                userInfo.getBackgroundImage(),
                                phone,
                                userInfo.getPostCount(),
                                userInfo.getFollowCount(),
                                userInfo.getFansCount(),
                                userInfo.getLikeCount(),
                                userInfo.getPointCount()
                        ));
                    });

        }
    }

    public void updateUserInfoLocal(String nickName) {
        if (nickName != null) {
            repository.getLocalDataSource().queryUserInfo(nickName)
                    .subscribeOn(Schedulers.io())
                    .subscribe(userInfo -> {
                                repository.getLocalDataSource().updateUserInfo(new UserInfo(
                                        userInfo.getId_user(),
                                        nickName,
                                        userInfo.getAccount(),
                                        userInfo.getEmail(),
                                        userInfo.getAvatarBackground(),
                                        userInfo.getBackgroundImage(),
                                        userInfo.getPhone(),
                                        userInfo.getPostCount(),
                                        userInfo.getFollowCount(),
                                        userInfo.getFansCount(),
                                        userInfo.getLikeCount(),
                                        userInfo.getPointCount()
                                )).subscribe(() -> {

                                }, error -> {
                                    throwableMutableLiveData.setValue(error);
                                });
                            }
                            , error -> {
                                throwableMutableLiveData.setValue(error);
                            });
        }
    }

    @SuppressLint("CheckResult")
    public void fetchUserInfoLocalData() {
        repository.getLocalDataSource().getUserInfoList().subscribe(userInfoList -> {
            if (userInfoList.size() > 0) {
                userInfoResult.postValue(dbInfoConvertToUser(userInfoList.get(0)));
//                Log.d("世界是一个bug", "fetchUserInfoLocalData    ：  " + userInfoList.get(0).toString());
            } else {
                userInfoResult.postValue(null);
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
//                    Log.d("世界是一个bug", "insertUserInfo ： " + "插入成功");

                }, error -> {
                    Log.d("世界是一个bug", "insertUserInfo ： " + error.toString());
                    Toast.makeText(getApplication(), "本地数据库插入失败", Toast.LENGTH_SHORT).show();
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
        repository.getLocalDataSource().getUserInfoList().subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    repository.getLocalDataSource().deleteUserInfo(result).subscribe(() -> {
//                        Log.d("世界是一个bug", "deleteAllUserInfo ： " + "插入成功");
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