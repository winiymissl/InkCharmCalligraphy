package com.example.module_character.ui.feature;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.common.base.BaseFragment;
import com.example.common.base.MyMMkv;
import com.example.common.glide.GlideEngine;
import com.example.common.util.Utils;
import com.example.module_character.R;
import com.example.module_character.databinding.FragmentCheckInBinding;
import com.example.module_character.ui.adapter.model.ChoosePicItem;
import com.example.module_character.ui.viewmodel.CheckInViewModel;
import com.google.android.material.transition.MaterialFade;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 14:15
 * @Version 1.0
 */
public class CheckInFragment extends BaseFragment<FragmentCheckInBinding> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CheckInViewModel mViewModel = new ViewModelProvider(this).get(CheckInViewModel.class);
        List<ChoosePicItem> list = new ArrayList<>();
        binding.ImageViewPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                PictureSelector.create(getActivity()).openGallery(SelectMimeType.ofImage()).setMaxSelectNum(1).setImageEngine(GlideEngine.createGlideEngine()).forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(ArrayList<LocalMedia> result) {
                        for (LocalMedia localMedia : result) {
                            list.add(new ChoosePicItem(new File(Utils.getFilePathFromUri(getActivity(), Uri.parse(localMedia.getAvailablePath())))));
                        }
                        Glide.with(getActivity()).load(list.get(0).getFile()).into(binding.ImageViewPic);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
        mViewModel.getmCheckInMutableLiveData().observe(getViewLifecycleOwner(), checkInResult -> {
            if (checkInResult == null) {
                return;
            }
            if (checkInResult.isSuccess()) {
                Utils.tryAgain("打卡成功", getActivity());
                NavHostFragment.findNavController(this).popBackStack();
            } else {
                Utils.tryAgain("打卡失败", getActivity());
            }
        });
        mViewModel.getThrowableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            Utils.tryAgain(throwable.getMessage(), getActivity());
        });
        binding.chipPost.setOnClickListener(v -> {
            try {
                mViewModel.checkIn(MyMMkv.getMyDefaultMMkv().getString("token", null), list.get(0).getFile(), binding.editTextContent.getText().toString(), 80);
            } catch (Exception e) {
                Utils.tryAgain("请补全信息", getActivity());
            }
        });
        binding.chipBack.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_in, container, false);
        binding = FragmentCheckInBinding.bind(view);
        return binding.getRoot();
    }
}
