package com.example.module_community.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
    private CommunityViewModel mViewModel;

    public static CreatePostFragment newInstance() {
        return new CreatePostFragment();
    }

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
        mViewModel = new ViewModelProvider(this).get(CommunityViewModel.class);

        List<ChoosePicItem> list = new ArrayList<>();
        PostRecyclerviewAdapter adapter = new PostRecyclerviewAdapter();
        binding.recyclerViewPic.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewPic.setAdapter(adapter);

        binding.recyclerViewPic.addOnItemTouchListener(new MyOnItemTouchListener(getActivity(), binding.recyclerViewPic, new MyOnItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == adapter.getList().size()) {
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
                } else {
                    /**
                     * 用于预览照片
                     ShapeableImageView shapeableImageView = binding.recyclerViewPic.findViewById(R.id.image_view_add);
                     FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(shapeableImageView, shapeableImageView.getTransitionName()).build();
                     Bundle bundle = new Bundle();
                     bundle.putSerializable("file", adapter.getList().get(position).getFile());
                     navController.navigate(R.id.preViewFragment, bundle, null, extras);*/
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
                if (position != adapter.getList().size()) {
                    binding.recyclerViewPic.smoothScrollToPosition(adapter.getList().size());
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                    builder.setTitle("提示").setMessage("是否删除？");
                    builder.setNeutralButton("close", (dialog, which) -> {
                    });
                    builder.setPositiveButton("删除", (dialog, which) -> {
                        adapter.deleteItem(position);
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        }));
        mViewModel.getPostResultMutableLiveData().observe(getViewLifecycleOwner(), result -> {
            if (result == null) {
                return;
            }
            if (result.isSuccess()) {
                Toast.makeText(getActivity(), "发布成功", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(this).popBackStack();
            }
        });
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
