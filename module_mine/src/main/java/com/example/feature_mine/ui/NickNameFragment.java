package com.example.feature_mine.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.common.base.BaseFragment;
import com.example.common.base.MyMMkv;
import com.example.common.eventbus.Event;
import com.example.common.eventbus.EventBusUtils;
import com.example.common.eventbus.EventCode;
import com.example.feature_mine.ui.viewmodel.MineViewModel;
import com.example.module_mine.R;
import com.example.module_mine.databinding.FragmentNickNameBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.transition.MaterialFade;

/**
 * @Author winiymissl
 * @Date 2024-04-12 19:27
 * @Version 1.0
 */
public class NickNameFragment extends BaseFragment<FragmentNickNameBinding> {
    private MineViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNickNameBinding.bind(inflater.inflate(R.layout.fragment_nick_name, container, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        NavController navController = NavHostFragment.findNavController(this);
        binding.editTextNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.inputDataChange(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.back.setOnClickListener(v -> {
            navController.popBackStack();
        });
        binding.save.setOnClickListener(v -> {
            /*
             *  进行网络修改
             * */
            if (!binding.editTextNickName.getText().toString().equals("")) {
                binding.progressIndicator.setVisibility(View.VISIBLE);
                mViewModel.changeUserInfoRemote(MyMMkv.getMyDefaultMMkv().getString("token", null), binding.editTextNickName.getText().toString());
            } else {
                Toast.makeText(mActivity, "不可为空", Toast.LENGTH_SHORT).show();
            }
        });
        mViewModel.getInputFormState().observe(getViewLifecycleOwner(), inputFormState -> {
            if (inputFormState == null) {
                return;
            }
            if (inputFormState.getNickNameError() != null) {
                binding.textInputLayoutNickName.setError(inputFormState.getNickNameError());
            } else {
                binding.textInputLayoutNickName.setError(null);
            }
        });
        mViewModel.getChangeUserInfoResultMutableLiveData().observe(getViewLifecycleOwner(), changeUserInfoResult -> {
            if (changeUserInfoResult.getCode() == 200) {
                /*
                 * 本地修改
                 * */
                mViewModel.changeUserInfoLocal(binding.editTextNickName.getText().toString());
            }
        });
        mViewModel.getNameMutableLiveData().observe(getViewLifecycleOwner(), name -> {
            binding.progressIndicator.setVisibility(View.GONE);
            EventBusUtils.sendEvent(new Event<>(EventCode.MORE_INFO, "needToChange"));
            navController.popBackStack();
        });
        mViewModel.getThrowableMutableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            tryAgain(throwable.getMessage());
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
}
