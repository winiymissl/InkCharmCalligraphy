package com.example.feature_mine.ui.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.base.BaseApplication;
import com.example.common.base.BaseViewModel;
import com.example.common.base.MyMMkv;
import com.example.common.dagger.AppComponent;
import com.example.common.livedata.SingleLiveEvent;
import com.example.feature_mine.dagger.DaggerMineComponent;
import com.example.feature_mine.dao.model.UserInfo;
import com.example.feature_mine.data.Repository;
import com.example.feature_mine.data.model.result.ChangeUserInfoResult;
import com.example.feature_mine.data.model.result.FansResult;
import com.example.feature_mine.data.model.result.FollowResult;
import com.example.feature_mine.data.model.result.UserInfoResult;
import com.example.feature_mine.ui.formstate.InputFormState;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class MineViewModel extends BaseViewModel {
    private MutableLiveData<UserInfoResult> userInfoResult = new MutableLiveData<>();
    private SingleLiveEvent<Throwable> throwableMutableLiveData = new SingleLiveEvent<>();

    private MutableLiveData<ChangeUserInfoResult> changeUserInfoResultMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<String> nameMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<InputFormState> inputFormState = new MutableLiveData<>();

    private MutableLiveData<FollowResult> followResultMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<FansResult> fansResultMutableLiveData = new MutableLiveData<>();


    public LiveData<FollowResult> getFollowResultMutableLiveData() {
        return followResultMutableLiveData;
    }

    public LiveData<FansResult> getFansResultMutableLiveData() {
        return fansResultMutableLiveData;
    }

    public LiveData<String> getNameMutableLiveData() {
        return nameMutableLiveData;
    }

    public LiveData<Throwable> getThrowableMutableLiveData() {
        return throwableMutableLiveData;
    }

    public LiveData<ChangeUserInfoResult> getChangeUserInfoResultMutableLiveData() {
        return changeUserInfoResultMutableLiveData;
    }

    public LiveData<InputFormState> getInputFormState() {
        return inputFormState;
    }

    public void inputDataChange(String nickName) {
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

    @SuppressLint("CheckResult")
    public void changeUserInfoRemote(String token, String name, String phone) {
        if (token != null) {
            repository.getRemoteDataSource().changeUserInfo(token, name, phone).subscribe(changeUserInfoResult -> {
                changeUserInfoResultMutableLiveData.postValue(changeUserInfoResult);
            }, error -> {
                Log.d("世界是一个bug", "changeUserInfoRemote    ：  " + error.toString());
                throwableMutableLiveData.postValue(error);
            });
        }
    }

    public void fetchFollow(String token, int page, int page_size) {
        repository.getRemoteDataSource().getFollow(token, page, page_size).subscribe(followResult -> {
            followResultMutableLiveData.postValue(followResult);
        }, error -> {
            throwableMutableLiveData.postValue(error);
        });
    }

    public void fetchFans(String token, int page, int page_size) {
        repository.getRemoteDataSource().getFans(token, page, page_size).subscribe(fansResult -> {
            fansResultMutableLiveData.postValue(fansResult);
        }, error -> {
            throwableMutableLiveData.postValue(error);
        });
    }


    @SuppressLint("CheckResult")
    public void changeUserInfoRemote(String token, String name) {
        if (token != null) {
            repository.getRemoteDataSource().changeUserInfo(token, name).subscribe(changeUserInfoResult -> {
                Log.d("世界是一个bug", changeUserInfoResult.toString());
                changeUserInfoResultMutableLiveData.postValue(changeUserInfoResult);
            }, error -> {
                Log.d("世界是一个bug", error.toString());
                throwableMutableLiveData.postValue(error);
            });
        }
    }

    @SuppressLint("CheckResult")

    public void changeUserInfoLocal(String nickName, String phone) {
        if (nickName != null) {
            repository.getLocalDataSource().queryUserInfo(nickName).subscribeOn(Schedulers.io()).subscribe(userInfo -> {
                repository.getLocalDataSource().updateUserInfo(new UserInfo(userInfo.getId_user(), nickName, userInfo.getAccount(), userInfo.getEmail(), userInfo.getAvatarBackground(),
                        userInfo.getBackgroundImage(),
                        phone, userInfo.getPostCount(), userInfo.getFollowCount(), userInfo.getFansCount(), userInfo.getLikeCount(), userInfo.getPointCount()));
            });
        }
    }

    @SuppressLint("CheckResult")
    public void changeUserInfoLocal(String nickName) {
        if (nickName != null) {
            repository.getLocalDataSource().getUserInfoList().subscribeOn(Schedulers.io()).subscribe(userInfo -> {
                UserInfo info = new UserInfo(nickName, userInfo.get(0).getId_user(), userInfo.get(0).getAccount(), userInfo.get(0).getEmail(), userInfo.get(0).getAvatarBackground(), userInfo.get(0).getBackgroundImage(), userInfo.get(0).getPhone(), userInfo.get(0).getPostCount(), userInfo.get(0).getFollowCount(), userInfo.get(0).getFansCount(), userInfo.get(0).getLikeCount(), userInfo.get(0).getPointCount());
                repository.getLocalDataSource().deleteUserInfo(userInfo).subscribe(() -> {
                    repository.getLocalDataSource().insertUserInfo(info).subscribe(() -> {
                        nameMutableLiveData.postValue("change");
                    }, error -> {
                        throwableMutableLiveData.postValue(error);
                        Log.d("世界是一个bug", "updateUserInfo    ：  " + error.toString());
                    });
                }, error -> {
                    throwableMutableLiveData.postValue(error);
                    Log.d("世界是一个bug", "deleteUserInfo    ：  " + error.toString());
                });

            }, error -> {
                throwableMutableLiveData.postValue(error);
                Log.d("世界是一个bug", "updateUserInfo    ：  " + error.toString());
            });
        }
    }

    @SuppressLint("CheckResult")
    public void fetchUserInfoLocalData() {
        repository.getLocalDataSource().getUserInfoList().subscribe(userInfoList -> {
            if (userInfoList.size() > 0) {
                userInfoResult.postValue(dbInfoConvertToUser(userInfoList.get(0)));
            } else {
                userInfoResult.postValue(null);
            }
        }, error -> {
            throwableMutableLiveData.postValue(error);
//            Toast.makeText(getApplication(), "本地数据库申请数据失败", Toast.LENGTH_SHORT).show();
            Log.d("世界是一个bug", "fetchUserInfoLocalData    ：  " + error.toString());
        });
    }

    @SuppressLint("CheckResult")
    public void fetchUserInfoRemoteData(String token) {
        if (token != null) {
            repository.getRemoteDataSource().getUserInfo(token).subscribe(result -> {
                repository.getLocalDataSource().getUserInfoList().subscribe(result_query -> {
                    userInfoResult.postValue(result);
                    if (result_query.isEmpty()) {
                        repository.getLocalDataSource().insertUserInfo(userInfoConvertToDb(result)).subscribe(() -> {
                        }, error -> {
                            Log.d("世界是一个bug", error.toString());
                            throwableMutableLiveData.postValue(error);
                        });
                    }
                }, error -> {
                    Log.d("世界是一个bug", error.toString());
                    throwableMutableLiveData.postValue(error);
                });
            }, error -> {
//                Log.d("世界是一个bug", error.toString());
                Log.d("世界是一个bug", "getRemoteDataSource ： " + error.toString());
                throwableMutableLiveData.postValue(error);
            });
        }
    }

    @SuppressLint("CheckResult")
    public void delUserInfo() {
        MyMMkv.getMyDefaultMMkv().remove("token");
        repository.getLocalDataSource().getUserInfoList()
                .subscribe(result -> {
                    repository.getLocalDataSource().deleteUserInfo(result).subscribe(() -> {
                        userInfoResult.postValue(null);
                    }, error -> {
                        Log.d("世界是一个bug", "deleteAllUserInfo ： " + error.toString());
                    });
                }, error -> {
                    Log.d("世界是一个bug", "queryUserInfo ： " + error.toString());
                });
    }

    private UserInfoResult dbInfoConvertToUser(UserInfo userInfoList) {
        return new UserInfoResult(new UserInfoResult.DataDTO(Integer.valueOf(userInfoList.getId_user()),
                userInfoList.getNickName(),
                userInfoList.getAccount(),
                userInfoList.getEmail(),
                userInfoList.getAvatarBackground(),
                userInfoList.getBackgroundImage(),
                userInfoList.getPhone(),
                Integer.valueOf(userInfoList.getPostCount()),
                Integer.valueOf(userInfoList.getFollowCount()),
                Integer.valueOf(userInfoList.getFansCount()),
                Integer.valueOf(userInfoList.getLikeCount()),
                Integer.valueOf(userInfoList.getPointCount())));
    }

    private UserInfo userInfoConvertToDb(UserInfoResult userInfo) {
        return new UserInfo(userInfo.getData().getNick_name(),
                String.valueOf(userInfo.getData().getId()),
                userInfo.getData().getAccount(),
                userInfo.getData().getEmail(),
                userInfo.getData().getAvatar_background(),
                userInfo.getData().getBackground_image(),
                userInfo.getData().getPhone(),
                String.valueOf(userInfo.getData().getPost_count()),
                String.valueOf(userInfo.getData().getFollow_count()),
                String.valueOf(userInfo.getData().getFans_count()),
                String.valueOf(userInfo.getData().getLike_count()),
                String.valueOf(userInfo.getData().getPoint_count()));
    }
}