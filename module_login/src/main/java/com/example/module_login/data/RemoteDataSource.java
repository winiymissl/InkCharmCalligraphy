package com.example.module_login.data;

import com.example.common.base.BaseApplication;
import com.example.common.dagger.AppComponent;
import com.example.module_login.dagger.DaggerLoginComponent;
import com.example.module_login.dagger.net.LoginAPI;
import com.example.module_login.data.model.CodeRequest;
import com.example.module_login.data.model.CodeResult;
import com.example.module_login.data.model.LoginUserRequest;
import com.example.module_login.data.model.LoginUserResult;
import com.example.module_login.data.model.RegisterUserRequest;
import com.example.module_login.data.model.RegisterUserResult;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author winiymissl
 * @Date 2024-04-04 17:42
 * @Version 1.0
 */
public class RemoteDataSource {
    @Inject
    LoginAPI loginAPI;


    @Inject
    public RemoteDataSource(LoginAPI loginAPI) {
        this.loginAPI = loginAPI;
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerLoginComponent.builder().appComponent(appComponent).build().injectTo(this);
    }

    public Observable<LoginUserResult> login(LoginUserRequest request) {
        Observable<LoginUserResult> login = null;
        try {
            login = loginAPI.login(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return login;
    }

    public Observable<RegisterUserResult> register(RegisterUserRequest request) {
        Observable<RegisterUserResult> register = null;
        try {
            register = loginAPI.register(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return register;
    }

    public Observable<CodeResult> getEmailCode(CodeRequest request) {
        Observable<CodeResult> code = null;
        try {
            code = loginAPI.getEmailCode(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return code;
    }
}
