package com.example.module_character.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.common.base.BaseFragment;
import com.example.module_character.R;
import com.example.module_character.databinding.FragmentScoreBinding;
import com.google.android.material.transition.MaterialFade;

/**
 * @Author winiymissl
 * @Date 2024-04-11 11:24
 * @Version 1.0
 */
public class ScoreFragment extends BaseFragment<FragmentScoreBinding> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentScoreBinding.bind(inflater.inflate(R.layout.fragment_score, container, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imageviewbutn.setOnClickListener(v -> {
            // 在按钮点击事件处理程序中添加以下代码
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePictureIntent.setType("image/*");
            startActivityForResult(takePictureIntent, 1);
        });
    }
}
