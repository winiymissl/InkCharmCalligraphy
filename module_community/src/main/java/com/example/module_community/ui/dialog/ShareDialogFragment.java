package com.example.module_community.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.module_community.R;
import com.example.module_community.databinding.DialogShareBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * @Author winiymissl
 * @Date 2024-04-16 17:19
 * @Version 1.0
 */
public class ShareDialogFragment extends BottomSheetDialogFragment {
    public static final String TAG = "ShareDialogFragment";

    private DialogShareBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_share, container, false);
        binding = DialogShareBinding.bind(view);
        return binding.getRoot();
    }
}