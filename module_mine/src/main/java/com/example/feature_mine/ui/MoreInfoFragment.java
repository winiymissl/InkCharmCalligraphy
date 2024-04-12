package com.example.feature_mine.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import com.example.common.base.BaseFragment;
import com.example.feature_mine.ui.viewmodel.MineViewModel;
import com.example.module_mine.R;
import com.example.module_mine.databinding.DialogNicknameBinding;
import com.example.module_mine.databinding.FragmentMoreInfoBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.transition.MaterialFade;

/**
 * @Author winiymissl
 * @Date 2024-04-11 23:13
 * @Version 1.0
 */
public class MoreInfoFragment extends BaseFragment<FragmentMoreInfoBinding> {
    private MineViewModel mViewModel;
    private DialogNicknameBinding binding_dialog;

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
            /*
             * 通过数据库加载数据
             * */
            binding.setItem(userInfoResult);
        });
        binding.constraintLayoutNickName.setOnClickListener(v -> {
            changeNickName();
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.dataChange(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        binding_dialog.etNickname.addTextChangedListener(textWatcher);
        mViewModel.getInputFormState().observe(getViewLifecycleOwner(), inputFormState -> {
            if (null == inputFormState) {
                return;
            }
            if (inputFormState.getNickNameError() != null) {
                binding_dialog.etNickname.setError(inputFormState.getNickNameError());
            }
        });
    }

    private void changeNickName() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("changeNickName").setView(R.layout.dialog_nickname);
        builder.setPositiveButton("sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*
                 * 点击修改
                 * */

                binding.loading.setVisibility(View.VISIBLE);
                mViewModel.getChangeUserInfoResultMutableLiveData().observe(getViewLifecycleOwner(), changeUserInfoResult -> {
                    /*
                     * 取消loading
                     * 设置在本地
                     * 设置在数据库
                     * */
                    binding.loading.setVisibility(View.GONE);
                    mViewModel.updateUserInfoLocal(binding_dialog.etNickname.toString());
//                    binding.setVariable(R.id.textView_nick_name, binding_dialog.etNickname.getText().toString());
                });
            }
        });
        builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMoreInfoBinding.bind(inflater.inflate(R.layout.fragment_more_info, container, false));
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.dialog_nickname, container, false);
        binding_dialog = DialogNicknameBinding.bind(view);
        return binding.getRoot();
    }
}
