package com.example.module_community.dagger;

import com.example.common.dagger.AppComponent;
import com.example.common.dagger.PreActivity;
import com.example.module_community.data.RemoteDataSource;
import com.example.module_community.ui.viewmodel.CommunityViewModel;

import dagger.Component;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:06
 * @Version 1.0
 */
@PreActivity
@Component(modules = {CommunityModule.class}, dependencies = {AppComponent.class})
public interface CommunityComponent {
    void injectTo(CommunityViewModel viewModel);

    void injectTo(RemoteDataSource remoteDataSource);
}
