package com.example.module_community.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.Explode;

import com.example.common.base.BaseFragment;
import com.example.module_community.R;
import com.example.module_community.databinding.FragmentCommunityDetailBinding;

/**
 * @Author winiymissl
 * @Date 2024-04-12 0:02
 * @Version 1.0
 */
public class CommunityDetailFragment extends BaseFragment<FragmentCommunityDetailBinding> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new Explode());
        setExitTransition(new Explode());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_detail, container, false);
        binding = FragmentCommunityDetailBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
