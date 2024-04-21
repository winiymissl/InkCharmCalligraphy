package com.example.module_character.dagger;

import com.example.common.dagger.AppComponent;
import com.example.common.dagger.PreActivity;
import com.example.module_character.data.RemoteDataSource;
import com.example.module_character.data.Repository;
import com.example.module_character.ui.viewmodel.CharacterViewModel;
import com.example.module_character.ui.viewmodel.CheckInViewModel;

import dagger.Component;

/**
 * @Author winiymissl
 * @Date 2024-04-17 21:11
 * @Version 1.0
 */

@PreActivity
@Component(modules = {CharacterModule.class}, dependencies = {AppComponent.class})
public interface CharacterComponent {
    void injectTo(CheckInViewModel viewModel);

    void injectTo(Repository repository);

    void injectTo(RemoteDataSource remoteDataSource);

    void injectTo(CharacterViewModel viewModel);
}
