package com.example.feature_mine.dagger;

import com.example.common.dagger.AppComponent;
import com.example.common.dagger.PreActivity;
import com.example.feature_mine.data.RemoteDataSource;
import com.example.feature_mine.ui.viewmodel.MineViewModel;

import dagger.Component;

/**
 * @Author winiymissl
 * @Date 2024-04-09 23:00
 * @Version 1.0
 */
@PreActivity
@Component(modules = {MineModule.class}, dependencies = {AppComponent.class})
public interface MineComponent {
    void injectTo(MineViewModel mineViewModel);

    void injectTo(RemoteDataSource remoteDataSource);
}