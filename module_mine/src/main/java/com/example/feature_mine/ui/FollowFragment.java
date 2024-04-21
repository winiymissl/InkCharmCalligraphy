package com.example.feature_mine.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.common.base.BaseFragment;
import com.example.common.base.MyMMkv;
import com.example.feature_mine.data.model.result.FollowResult;
import com.example.feature_mine.ui.adapter.FollowRecyclerviewAdapter;
import com.example.feature_mine.ui.adapter.model.FollowItem;
import com.example.feature_mine.ui.viewmodel.MineViewModel;
import com.example.module_mine.R;
import com.example.module_mine.databinding.FragmentFollowBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 21:22
 * @Version 1.0
 */
public class FollowFragment extends BaseFragment<FragmentFollowBinding> {
    private MineViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        FollowRecyclerviewAdapter adapter = new FollowRecyclerviewAdapter();
        mViewModel.fetchFollow(MyMMkv.getMyDefaultMMkv().getString("token", null), 1, 10);
        binding.recyclerViewFollow.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        binding.recyclerViewFollow.setAdapter(adapter);
        mViewModel.getFollowResultMutableLiveData().observe(getViewLifecycleOwner(), result -> {
            if (result == null) {
                return;
            }
            if (result.getData().getPost_data() != null) {
                adapter.setData(net2Follow(result));
            }
        });
        binding.chip.setOnClickListener(v->{
            NavHostFragment.findNavController(this).popBackStack();
        });
    }

    private List<FollowItem> net2Follow(FollowResult result) {
        List<FollowItem> list = new ArrayList<>();
        result.getData().getPost_data().forEach(item -> {
            list.add(new FollowItem(item.getUser_id(), item.getAvatar_background(), item.getNick_name(), item.getFollow_count(), item.getFans_count(), item.getEmail()));
        });
        return list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow, container, false);
        binding = FragmentFollowBinding.bind(view);
        return binding.getRoot();
    }
}
