package com.example.module_community.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.AutoTransition;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.common.base.BaseFragment;
import com.example.module_community.R;
import com.example.module_community.databinding.FragmentPreViewBinding;

import java.io.File;

/**
 * @Author winiymissl
 * @Date 2024-04-17 12:31
 * @Version 1.0
 */
public class PreViewFragment extends BaseFragment<FragmentPreViewBinding> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(new AutoTransition());
        setSharedElementReturnTransition(new AutoTransition());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postponeEnterTransition();
        Bundle bundle = getArguments();
        if (bundle != null) {
            File file = (File) bundle.get("file");
            Glide.with(binding.imageViewPreView).load(file).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                    startPostponedEnterTransition();
                    return false;
                }

                @Override
                public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                    startPostponedEnterTransition();
                    return false;
                }
            }).into(binding.imageViewPreView);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pre_view, container, false);
        binding = FragmentPreViewBinding.bind(view);
        return binding.getRoot();
    }
}
