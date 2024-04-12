package com.example.feature_mine.ui.bottomsheetfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.module_mine.R;
import com.example.module_mine.databinding.FragmentBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.transition.MaterialFade;

/**
 * @Author winiymissl
 * @Date 2024-04-12 10:59
 * @Version 1.0
 */
public class ModalBottomSheet extends BottomSheetDialogFragment {
    public static final String TAG = "ModalBottomSheet";
    private FragmentBottomSheetBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        binding = FragmentBottomSheetBinding.bind(view);
        NavController navController = NavHostFragment.findNavController(this);
        binding.more.setOnClickListener(v -> {
            navController.navigate(R.id.moreInfoFragment);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
