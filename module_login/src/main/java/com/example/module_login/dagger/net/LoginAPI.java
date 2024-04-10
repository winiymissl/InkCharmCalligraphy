package com.example.module_login.dagger.net;

import com.example.module_login.data.model.request.CodeRequest;
import com.example.module_login.data.model.request.LoginUserRequest;
import com.example.module_login.data.model.request.RegisterUserRequest;
import com.example.module_login.data.model.result.CodeResult;
import com.example.module_login.data.model.result.LoginUserResult;
import com.example.module_login.data.model.result.RegisterUserResult;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author winiymissl
 * @Date 2024-04-04 16:52
 * @Version 1.0
 */
public interface LoginAPI {
    @POST("/user/login")
    Observable<LoginUserResult> login(@Body LoginUserRequest request);

    @POST("/user/register")
    Observable<RegisterUserResult> register(@Body RegisterUserRequest request);

    @POST("/user/basic/getemailverification")
    Observable<CodeResult> getEmailCode(@Body CodeRequest request);
}
