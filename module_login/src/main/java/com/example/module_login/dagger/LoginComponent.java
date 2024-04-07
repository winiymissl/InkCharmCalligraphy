package com.example.module_login.dagger;

import com.example.common.dagger.AppComponent;
import com.example.common.dagger.PreActivity;
import com.example.module_login.data.RemoteDataSource;
import com.example.module_login.ui.login.viewmodel.LoginViewModel;
import com.example.module_login.ui.register.viewmodel.RegisterViewModel;

import dagger.Component;

/**
 * @Author winiymissl
 * @Date 2024-04-04 16:53
 * @Version 1.0
 */
@PreActivity
@Component(dependencies = AppComponent.class, modules = {LoginModule.class})
public interface LoginComponent {
    void injectTo(RegisterViewModel registerViewModel);

    void injectTo(RemoteDataSource remoteDataSource);

    void injectTo(LoginViewModel loginViewModel);
}
