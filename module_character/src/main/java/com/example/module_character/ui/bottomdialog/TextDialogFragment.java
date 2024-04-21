package com.example.module_character.ui.bottomdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.module_character.R;
import com.example.module_character.databinding.DialogTextBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.transition.MaterialFade;

/**
 * @Author winiymissl
 * @Date 2024-04-21 19:33
 * @Version 1.0
 */
public class TextDialogFragment extends BottomSheetDialogFragment {
    private DialogTextBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        String text = arguments.getString("text", null);
        if (text != null) {
            binding.textViewContent.setText(text);
        } else {
            binding.textViewContent.setText("未识别");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_text, container, false);
        binding = DialogTextBinding.bind(view);
        return binding.getRoot();
    }
}

