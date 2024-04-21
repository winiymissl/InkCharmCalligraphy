package com.example.module_character.ui.feature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.common.base.BaseFragment;
import com.example.module_character.R;
import com.example.module_character.databinding.FragmentMatchBinding;
import com.example.module_character.ui.adapter.MatchAdapter;
import com.example.module_character.ui.adapter.model.MatchItem;
import com.google.android.material.transition.MaterialFade;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * @Author winiymissl
 * @Date 2024-04-17 21:06
 * @Version 1.0
 */

public class MatchFragment extends BaseFragment<FragmentMatchBinding> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MatchAdapter adapter = new MatchAdapter();
        adapter.setData(MatchItem.getData());
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(adapter);
        animationAdapter.setDuration(800);
        animationAdapter.setInterpolator(new OvershootInterpolator());
        animationAdapter.setFirstOnly(false);
        binding.recyclerViewMatch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerViewMatch.setAdapter(animationAdapter);
        binding.back.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        binding = FragmentMatchBinding.bind(view);
        return binding.getRoot();
    }
}