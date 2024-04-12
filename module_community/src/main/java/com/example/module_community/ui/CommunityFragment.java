package com.example.module_community.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.transition.Explode;

import com.example.common.base.BaseFragment;
import com.example.module_community.R;
import com.example.module_community.data.model.result.CommunityInfoResult;
import com.example.module_community.databinding.FragmentCommunityBinding;
import com.example.module_community.ui.adapter.RecyclerviewCommunityAdapter;
import com.example.module_community.ui.adapter.model.CommunityItem;
import com.example.module_community.ui.viewmodel.CommunityViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class CommunityFragment extends BaseFragment<FragmentCommunityBinding> {

    private CommunityViewModel mViewModel;

    public static CommunityFragment newInstance() {
        return new CommunityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new Explode());
        setExitTransition(new Explode());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        binding = FragmentCommunityBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            mViewModel = new ViewModelProvider(this).get(CommunityViewModel.class);
        } catch (Exception e) {
            Log.d("世界是一个bug", e.toString());
        }
        RecyclerviewCommunityAdapter adapter = new RecyclerviewCommunityAdapter();
        binding.recyclerViewCommunity.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerViewCommunity.setAdapter(adapter);

        binding.smartRefreshLayoutCommunity.setOnRefreshListener(refreshLayout -> binding.smartRefreshLayoutCommunity.finishRefresh());
        mViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), result -> {
            if (result == null) {
                return;
            }
            adapter.setData(net2ui(result));
        });
        mViewModel.fetchRemoteDataSource(0, 10);
        mViewModel.getThrowableMutableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            tryAgain(throwable.toString());
        });
    }

    private void tryAgain(String message) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("提示").setMessage(message);
        builder.setNeutralButton("close", (dialog, which) -> {

        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private List<CommunityItem> net2ui(CommunityInfoResult result) {
        List<CommunityItem> list = new ArrayList<>();
        result.getData().getPostData().forEach(postDataDTO -> {
            list.add(new CommunityItem(postDataDTO.getImageUrls().get(0), postDataDTO.getUserInfo().getNickName(), postDataDTO.getContent(), postDataDTO.getUserInfo().getAvatarImage()));
        });
        return list;
    }
}