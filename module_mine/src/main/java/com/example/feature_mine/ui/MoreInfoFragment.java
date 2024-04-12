package com.example.feature_mine.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.common.base.BaseFragment;
import com.example.common.eventbus.BindEventBus;
import com.example.common.eventbus.Event;
import com.example.common.eventbus.EventCode;
import com.example.feature_mine.ui.viewmodel.MineViewModel;
import com.example.module_mine.R;
import com.example.module_mine.databinding.FragmentMoreInfoBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.transition.MaterialFade;

import org.greenrobot.eventbus.Subscribe;

/**
 * @Author winiymissl
 * @Date 2024-04-11 23:13
 * @Version 1.0
 */
@BindEventBus
public class MoreInfoFragment extends BaseFragment<FragmentMoreInfoBinding> {
    private MineViewModel mViewModel;

    @Subscribe
    public void onMessageEvent(Event event) {
        if (EventCode.MORE_INFO == event.getCode()) {
            mViewModel.fetchUserInfoLocalData();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        mViewModel.getUserInfoResult().observe(getViewLifecycleOwner(), userInfoResult -> {
            /**
             * 通过数据库 dataBinding加载数据
             * */
            binding.setItem(userInfoResult);
        });
        binding.constraintLayoutNickName.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.nickNameFragment);
        });

        mViewModel.getThrowableMutableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            binding.loadingOverlay.setVisibility(View.GONE);
            tryAgain(throwable.toString());
        });
    }


    private void tryAgain(String message) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("提示").setMessage(message);
        builder.setNeutralButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMoreInfoBinding.bind(inflater.inflate(R.layout.fragment_more_info, container, false));
        return binding.getRoot();
    }
}
