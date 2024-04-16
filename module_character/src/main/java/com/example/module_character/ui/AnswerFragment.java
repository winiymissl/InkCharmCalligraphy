package com.example.module_character.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.common.base.BaseFragment;
import com.example.module_character.R;
import com.example.module_character.databinding.FragmentAnswerBinding;

/**
 * @Author winiymissl
 * @Date 2024-04-15 20:04
 * @Version 1.0
 */
public class AnswerFragment extends BaseFragment<FragmentAnswerBinding> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        binding.imageViewAppCompat.setImageBitmap(bitmap);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer, container, false);
        binding = FragmentAnswerBinding.bind(view);
        return binding.getRoot();
    }
}
