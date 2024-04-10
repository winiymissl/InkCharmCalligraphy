package com.example.module_login.ui.register;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.transition.Explode;

import com.example.common.base.BaseFragment;
import com.example.module_login.R;
import com.example.module_login.databinding.FragmentRegisterBinding;
import com.example.module_login.ui.register.viewmodel.RegisterViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> {

    private RegisterViewModel mViewModel;

    private Disposable disposable;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        setSharedElementEnterTransition(TransitionInflater.from(requireContext()).inflateTransition(R.transition.transition_fragment));
//        setSharedElementReturnTransition(TransitionInflater.from(requireContext()).inflateTransition(R.transition.transition_fragment));
        setEnterTransition(new Explode());
        setExitTransition(new Explode());
        binding = FragmentRegisterBinding.bind(inflater.inflate(R.layout.fragment_register, container, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding.register.setOnClickListener(v -> {
            binding.loading.setVisibility(View.VISIBLE);
            mViewModel.register(binding.editTextEmail.getText().toString(), binding.editTextRegisterPassword.getText().toString(), binding.editTextLoginCode.getText().toString());
        });
        mViewModel.getRegisterFormStateMutableLiveData().observe(getViewLifecycleOwner(), state -> {
            if (state == null) {
                return;
            }
            /*
            控制注册是否可以用，这是一个注册形式的判断
             */
            binding.register.setEnabled(state.isDataValid());
            if (state.getUsernameError() != null) {
                binding.textInputLayoutEmail.setError(getString(state.getUsernameError()));
            } else {
                binding.textInputLayoutEmail.setError(null);
                binding.buttonCode.setEnabled(true);
            }
            if (state.getPasswordError() != null) {
                binding.textinputLayoutPassword.setError(getString(state.getPasswordError()));
            } else {
                binding.textinputLayoutPassword.setError(null);
            }
            if (state.getCodeError() != null) {
                binding.textinputLayoutCode.setError(getString(state.getCodeError()));
            } else {
                binding.textinputLayoutCode.setError(null);
            }
        });

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.dataChanged(binding.editTextEmail.getText().toString(), binding.editTextRegisterPassword.getText().toString(), binding.editTextLoginCode.getText().toString());
            }
        };
        binding.editTextRegisterPassword.addTextChangedListener(watcher);
        binding.editTextLoginCode.addTextChangedListener(watcher);
        binding.editTextEmail.addTextChangedListener(watcher);
        binding.buttonCode.setOnClickListener(v -> {
            mViewModel.sendCode(binding.editTextEmail.getText().toString());
            // 开始倒计时
            startCountdown();
        });
        mViewModel.getCode().observe(getViewLifecycleOwner(), s -> {
            if (s == null) {
                return;
            }
            updateCodeState(s.getCode());
        });
        mViewModel.getThrowableMutableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            binding.loading.setVisibility(View.GONE);
            tryAgain();
        });
        mViewModel.getRegisterResultMutableLiveData().observe(getViewLifecycleOwner(), registerResult -> {
            if (registerResult == null) {
                return;
            }
            binding.loading.setVisibility(View.GONE);
            if (registerResult.getCode() == 200) {
                NavController navController = NavHostFragment.findNavController(this);
                navController.popBackStack();
            } else {
                Toast.makeText(getContext().getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tryAgain() {

    }

    private void updateCodeState(int code) {
        if (code == 200) {
            Toast.makeText(getContext(), "发送成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "发送失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void startCountdown() {
        binding.buttonCode.setVisibility(View.GONE);
        disposable = Observable.intervalRange(1, 10, 0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                // onNext，每过一秒更新按钮上的文本
                aLong -> {
                    binding.buttonCode.setText(String.format("%ds", 60 - aLong));
                },

                // onError，倒计时完成后重新设置按钮为可点击状态
                Throwable::printStackTrace,
                // onComplete，倒计时完成后重新设置按钮为可点击状态
                () -> {
                    binding.buttonCode.setVisibility(View.VISIBLE);
                    binding.buttonCode.setText("验证码");
                });
    }


    void updateUi() {
        String welcome = getString(R.string.welcome);
        // TODO : initiate successful logged in experience
        if (getContext().getApplicationContext() != null) {
            Toast.makeText(getContext().getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        }
    }

    void showFailure(Integer error) {
        String fail = getString(R.string.register_failure);
        // TODO : initiate successful logged in experience
        if (getContext().getApplicationContext() != null) {
            Toast.makeText(getContext().getApplicationContext(), fail, Toast.LENGTH_LONG).show();
        }
    }
}