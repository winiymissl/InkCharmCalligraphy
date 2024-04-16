package com.example.module_community.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.Explode;

import com.example.common.base.BaseFragment;
import com.example.common.base.MyMMkv;
import com.example.common.glide.GlideEngine;
import com.example.common.recyclerview.MyOnItemTouchListener;
import com.example.common.util.Utils;
import com.example.module_community.R;
import com.example.module_community.databinding.FragmentCreatePostBinding;
import com.example.module_community.ui.adapter.PostRecyclerviewAdapter;
import com.example.module_community.ui.adapter.model.ChoosePicItem;
import com.example.module_community.ui.viewmodel.CommunityViewModel;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-16 19:13
 * @Version 1.0
 */
public class CreatePostFragment extends BaseFragment<FragmentCreatePostBinding> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new Explode());
        setExitTransition(new Explode());

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        binding = FragmentCreatePostBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommunityViewModel mViewModel = new ViewModelProvider(this).get(CommunityViewModel.class);
        List<ChoosePicItem> list = new ArrayList<>();
        PostRecyclerviewAdapter adapter = new PostRecyclerviewAdapter();
        binding.recyclerViewPic.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewPic.setAdapter(adapter);
        binding.recyclerViewPic.addOnItemTouchListener(new MyOnItemTouchListener(getContext(), (view1, position) -> {
            if (position == list.size()) {
                list.clear();
                PictureSelector.create(getActivity()).openGallery(SelectMimeType.ofImage()).setImageEngine(GlideEngine.createGlideEngine()).forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(ArrayList<LocalMedia> result) {
                        for (LocalMedia localMedia : result) {
                            list.add(new ChoosePicItem(new File(Utils.getFilePathFromUri(getActivity(), Uri.parse(localMedia.getAvailablePath())))));
                        }
                        adapter.setList(list);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        }));
        binding.chipPost.setOnClickListener(v -> {
            mViewModel.postData(MyMMkv.getMyDefaultMMkv().getString("token", null), item2File(adapter.getList()), binding.editTextContent.getText().toString());
        });
        mViewModel.getThrowableMutableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            Utils.tryAgain(throwable.toString(), getHoldingsActivity());
        });
        binding.chipBack.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });

    }

    private List<File> item2File(List<ChoosePicItem> list) {
        List<File> files = new ArrayList<>();
        list.forEach(item -> {
            files.add(item.getFile());
        });
        return files;
    }
}
