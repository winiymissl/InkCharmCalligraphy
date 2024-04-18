package com.example.feature_mine.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.common.base.BaseFragment;
import com.example.common.base.MyMMkv;
import com.example.feature_mine.data.model.result.FansResult;
import com.example.feature_mine.ui.adapter.FansReceyclerviewAdapter;
import com.example.feature_mine.ui.adapter.model.FansItem;
import com.example.feature_mine.ui.viewmodel.MineViewModel;
import com.example.module_mine.R;
import com.example.module_mine.databinding.FragmentFansBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 21:07
 * @Version 1.0
 */
public class FansFragment extends BaseFragment<FragmentFansBinding> {
    private MineViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        FansReceyclerviewAdapter adapter = new FansReceyclerviewAdapter();
        mViewModel.fetchFans(MyMMkv.getMyDefaultMMkv().getString("token", null), 1, 10);
        mViewModel.getFansResultMutableLiveData().observe(getViewLifecycleOwner(), result -> {
            if (result == null) {
                return;
            }
            adapter.setData(net2fans(result));
        });
    }

    private List<FansItem> net2fans(FansResult result) {
        List<FansItem> list = new ArrayList<>();
        result.getData().getPostData().forEach(item -> {
            list.add(new FansItem(item.getUserId(), item.getAvatarBackground(), item.getNickName(), item.getFollowCount(), item.getFansCount(), item.getEmail()));
        });
        return list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fans, container, false);
        binding = FragmentFansBinding.bind(view);
        return binding.getRoot();
    }
}
