package com.example.module_login.data;

import com.example.module_login.data.model.CodeRequest;
import com.example.module_login.data.model.CodeResult;
import com.example.module_login.data.model.LoginUserRequest;
import com.example.module_login.data.model.LoginUserResult;
import com.example.module_login.data.model.RegisterUserRequest;
import com.example.module_login.data.model.RegisterUserResult;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class Repository {
    private final RemoteDataSource remoteDataSource;

    //    private static volatile Repository instance;

    @Inject
    public Repository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public Observable<LoginUserResult> login(String email, String password) {
        return remoteDataSource.login(new LoginUserRequest(email, password));
    }

    public Observable<RegisterUserResult> register(String email, String password, String email_code) {
        return remoteDataSource.register(new RegisterUserRequest(email, password, email_code));
    }

    public Observable<CodeResult> getEmailCode(String email) {
        return remoteDataSource.getEmailCode(new CodeRequest(email));
    }
}