package com.example.module_character.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.Slide;

import com.example.common.base.BaseFragment;
import com.example.module_character.R;
import com.example.module_character.databinding.FragmentHistoryScoreBinding;

/**
 * @Author winiymissl
 * @Date 2024-04-15 21:49
 * @Version 1.0
 */
public class HistoryScoreFragment extends BaseFragment<FragmentHistoryScoreBinding> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setEnterTransition(new Slide(Gravity.RIGHT));
        setExitTransition(new Slide(Gravity.RIGHT));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_score, container, false);
        binding = FragmentHistoryScoreBinding.bind(view);
        return binding.getRoot();
    }
}
