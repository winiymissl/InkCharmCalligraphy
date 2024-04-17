package com.example.module_community.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.transition.Explode;

import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
import com.ethanhua.skeleton.Skeleton;
import com.example.common.base.BaseFragment;
import com.example.module_community.R;
import com.example.module_community.databinding.FragmentCommunityBinding;
import com.example.module_community.ui.adapter.RecyclerviewCommunityAdapter;
import com.example.module_community.ui.viewmodel.CommunityViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class CommunityFragment extends BaseFragment<FragmentCommunityBinding> {
    public static final String LOADING_FIL = "load_fail";

    private CommunityViewModel mViewModel;

    private int page = 1;

    public static CommunityFragment newInstance() {
        return new CommunityFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new Explode());
        setExitTransition(new Explode());
    }

    ScaleInAnimationAdapter adapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        binding = FragmentCommunityBinding.bind(view);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CommunityViewModel.class);

        RecyclerviewCommunityAdapter adapter_temp = new RecyclerviewCommunityAdapter((view1, position, list) -> {
            int id = list.get(position).getPost_id();
            Bundle bundle = new Bundle();
            bundle.putInt("post_id", id);
            Log.d("世界是一个bug", String.valueOf(id));
            NavHostFragment.findNavController(this).navigate(R.id.communityDetailFragment, bundle);
        });
        adapter = new ScaleInAnimationAdapter(adapter_temp);
        adapter.setDuration(800);
        adapter.setInterpolator(new OvershootInterpolator());
        adapter.setFirstOnly(false);
        binding.recyclerViewCommunity.setHasFixedSize(true);

        binding.recyclerViewCommunity.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerViewCommunity.setAdapter(adapter);
        /**
         * 骨骼加载
         * */
        RecyclerViewSkeletonScreen skeleton = Skeleton.bind(binding.recyclerViewCommunity).adapter(adapter).load(R.layout.item_skeleton).show();

        mViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), result -> {
            if (result == null) {
                return;
            }
            skeleton.hide();
            adapter_temp.setData(result);
            binding.smartRefreshLayoutCommunity.finishRefresh();
            binding.smartRefreshLayoutCommunity.finishLoadMore();
        });
        binding.smartRefreshLayoutCommunity.autoRefresh();
        mViewModel.getThrowableMutableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            if (throwable.getMessage().equals(LOADING_FIL)) {

            } else {
                tryAgain(throwable.toString());
            }
            binding.smartRefreshLayoutCommunity.finishRefresh();
            binding.smartRefreshLayoutCommunity.finishLoadMore(false);
        });
        binding.smartRefreshLayoutCommunity.setOnRefreshListener(refreshLayout -> {
            skeleton.show();
            page = 1;
            refreshLayout.setEnableLoadMore(true);
            mViewModel.fetchRemoteDataSource(page, 10);
        });
        binding.smartRefreshLayoutCommunity.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mViewModel.fetchRemoteDataSource(page, 10);
        });
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.post) {
                NavHostFragment.findNavController(this).navigate(R.id.createPostFragment);
                return true;
            }
            return false;
        });
//        mViewModel.flowable.to(autoDisposable(AndroidLifecycleScopeProvider.from(this))).subscribe(pagingData -> {
//            adapter_temp.submitData(this.getLifecycle(), pagingData);
//        }, error -> {
//            Log.d("世界是一个bug", error.toString());
//        });
    }


    private void tryAgain(String message) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("提示").setMessage(message);
        builder.setNeutralButton("close", (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}