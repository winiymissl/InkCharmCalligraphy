package com.example.module_login.dagger.net;

import com.example.module_login.data.model.CodeRequest;
import com.example.module_login.data.model.CodeResult;
import com.example.module_login.data.model.LoginUserRequest;
import com.example.module_login.data.model.LoginUserResult;
import com.example.module_login.data.model.RegisterUserRequest;
import com.example.module_login.data.model.RegisterUserResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author winiymissl
 * @Date 2024-04-04 16:52
 * @Version 1.0
 */
public interface LoginAPI {
    @POST("api/user/login")
    Observable<LoginUserResult> login(@Body LoginUserRequest request);

    @POST("api/user/register")
    Observable<RegisterUserResult> register(@Body RegisterUserRequest request);

    @POST("api/basic/getemailverification")
    Observable<CodeResult> getEmailCode(@Body CodeRequest request);
}
