package com.example.module_character.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.common.base.BaseFragment;
import com.example.module_character.databinding.FragmentCharacterBinding;

/**
 * @Author winiymissl
 * @Date 2024-04-10 22:38
 * @Version 1.0
 */
public class CharacterFragment extends BaseFragment<FragmentCharacterBinding> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
