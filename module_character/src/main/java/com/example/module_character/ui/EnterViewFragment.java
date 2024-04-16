package com.example.module_character.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.ChangeTransform;

import com.example.common.base.BaseFragment;
import com.example.module_character.R;
import com.example.module_character.databinding.FragmentEnterViewBinding;
import com.google.android.material.transition.MaterialFade;

/**
 * @Author winiymissl
 * @Date 2024-04-15 22:03
 * @Version 1.0
 */
public class EnterViewFragment extends BaseFragment<FragmentEnterViewBinding> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
        setSharedElementEnterTransition(new ChangeTransform());
        setSharedElementReturnTransition(new ChangeTransform());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_view, container, false);
        binding = FragmentEnterViewBinding.bind(view);
        return binding.getRoot();
    }
}
