package com.example.module_login.ui.login.viewmodel;

import android.app.Application;
import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.base.BaseApplication;
import com.example.common.base.BaseViewModel;
import com.example.common.dagger.AppComponent;
import com.example.module_login.dagger.DaggerLoginComponent;
import com.example.module_login.data.Repository;
import com.example.module_login.data.model.LoginUserResult;
import com.example.module_login.ui.login.LoginFormState;
import com.example.module_login.R;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {

    private final CompositeDisposable disposable = new CompositeDisposable();

    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<LoginUserResult> loginResult = new MutableLiveData<>();
    @Inject
    Repository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void init() {
        super.init();
        try {
            AppComponent appComponent = BaseApplication.getAppComponent();
            DaggerLoginComponent.builder()
                    .appComponent(appComponent)
                    .build()
                    .injectTo(this);
        } catch (Exception e) {
            Log.d("世界是一个bug", e.toString());
        }
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<LoginUserResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        repository.login(username, password)
                .subscribe(
                        loginUserResult -> loginResult.setValue(loginUserResult),
                        throwable -> {
                            Log.d("世界是一个bug", throwable.toString());
                            throwableMutableLiveData.setValue(throwable);
                        });
    }

    public void loginDataChanged(String email, String password) {
        if (!isEmailValid(email)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_email, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            if (email.length() == 0 || password.length() == 0) {
                loginFormState.setValue(new LoginFormState(false));
            } else {
                loginFormState.setValue(new LoginFormState(true));
            }
        }
    }

    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.length() == 0) {
            return true;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            /*
            用于检验只有空格是否为error
             */
//            return !email.trim().isEmpty();
            return false;
        }
    }

    private boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        if (password.length() == 0) {
            return true;
        }

        return password.trim().length() > 5;
    }
}