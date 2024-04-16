package com.example.module_login.ui.register.viewmodel;

import android.app.Application;
import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.common.base.BaseApplication;
import com.example.common.base.BaseViewModel;
import com.example.common.dagger.AppComponent;
import com.example.common.livedata.SingleLiveEvent;
import com.example.module_login.R;
import com.example.module_login.dagger.DaggerLoginComponent;
import com.example.module_login.data.Repository;
import com.example.module_login.data.model.result.CodeResult;
import com.example.module_login.data.model.result.RegisterUserResult;
import com.example.module_login.ui.register.RegisterFormState;

import javax.inject.Inject;

public class RegisterViewModel extends BaseViewModel {
    private MutableLiveData<RegisterFormState> registerFormStateMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<RegisterUserResult> registerResultMutableLiveData = new MutableLiveData<>();

    private SingleLiveEvent<Throwable> throwableMutableLiveData = new SingleLiveEvent<>();
    public LiveData<Throwable> getThrowableMutableLiveData() {
        return throwableMutableLiveData;
    }

    private MutableLiveData<CodeResult> code = new MutableLiveData<>();


    @Inject
    Repository repository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void init() {
        super.init();
        /*
        进行dagger注入
         */
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

    public LiveData<RegisterUserResult> getRegisterResultMutableLiveData() {
        return registerResultMutableLiveData;
    }

    public LiveData<RegisterFormState> getRegisterFormStateMutableLiveData() {
        return registerFormStateMutableLiveData;
    }

    public LiveData<CodeResult> getCode() {
        return code;
    }

    public void register(String email, String password, String email_code) {
        Log.d("世界是一个bug", email + "     " + password + "     " + email_code);
        repository.register(email, password, email_code)
                .subscribe(registerUserResult -> {
                            Log.d("世界是一个bug", registerUserResult.toString());
                            registerResultMutableLiveData.setValue(registerUserResult);
                        },
                        throwable -> {
                            Log.d("世界是一个bug", "register   :  " + throwable.toString());
                            throwableMutableLiveData.setValue(throwable);
                        });
    }

    public void sendCode(String email) {
        Log.d("世界是一个bug", email);

        repository.getEmailCode(email)
                .subscribe(codeResult -> {
                            code.setValue(codeResult);
                            Log.d("世界是一个bug", codeResult.toString());
                        },
                        throwable -> {
                            Log.d("世界是一个bug", throwable.toString());
                            throwableMutableLiveData.setValue(throwable);
                        });
    }

    public void dataChanged(String email, String password, String code) {
        if (!isEmailValid(email)) {
            registerFormStateMutableLiveData.setValue(new RegisterFormState(R.string.invalid_email, null, null));
        } else if (!isPasswordValid(password)) {
            registerFormStateMutableLiveData.setValue(new RegisterFormState(null, R.string.invalid_password, null));
        } else if (isCodeValid(code)) {
            registerFormStateMutableLiveData.setValue(new RegisterFormState(null, null, R.string.invalid_code));
        } else {
            registerFormStateMutableLiveData.setValue(new RegisterFormState(true));
        }
    }

    private boolean isCodeValid(String code) {
        return code != null && code.trim().isEmpty();
    }

    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
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

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        return password.trim().length() > 5;
    }
}