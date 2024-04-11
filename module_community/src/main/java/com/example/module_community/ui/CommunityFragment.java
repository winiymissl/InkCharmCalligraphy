package com.example.module_community.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.transition.Explode;

import com.example.common.base.BaseFragment;
import com.example.module_community.databinding.FragmentCommunityBinding;
import com.example.module_community.ui.adapter.RecyclerviewCommunityAdapter;
import com.example.module_community.ui.adapter.model.CommunityItem;
import com.example.module_community.ui.viewmodel.CommunityViewModel;

public class CommunityFragment extends BaseFragment<FragmentCommunityBinding> {

    private CommunityViewModel mViewModel;

    public static CommunityFragment newInstance() {
        return new CommunityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setEnterTransition(new Explode());
        setExitTransition(new Explode());
        binding = FragmentCommunityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerviewCommunityAdapter adapter = new RecyclerviewCommunityAdapter();
        adapter.setData(CommunityItem.getCommunityItem());
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }
}