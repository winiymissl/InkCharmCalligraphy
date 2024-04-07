package com.example.module_login.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.transition.Explode;

import com.example.common.base.BaseFragment;
import com.example.module_login.R;
import com.example.module_login.databinding.FragmentLoginBinding;
import com.example.module_login.ui.login.viewmodel.LoginViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    private LoginViewModel loginViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
//        setSharedElementEnterTransition(TransitionInflater.from(requireContext()).inflateTransition(R.transition.transition_fragment));
//        setSharedElementReturnTransition(TransitionInflater.from(requireContext()).inflateTransition(R.transition.transition_fragment));
        setEnterTransition(new Explode());
        setExitTransition(new Explode());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        final TextInputEditText usernameEditText = binding.email;
        final TextInputEditText passwordEditText = binding.loginPassword;
        final MaterialButton loginButton = binding.buttonLogin;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(getViewLifecycleOwner(), new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    binding.textInputLayoutEmail.setError(getString(loginFormState.getUsernameError()));
                } else {
                    binding.textInputLayoutEmail.setError(null);
                }
                if (loginFormState.getPasswordError() != null) {
                    binding.textinputLayoutPassword.setError(getString(loginFormState.getPasswordError()));
                } else {
                    binding.textinputLayoutPassword.setError(null);
                }
            }
        });
        loginViewModel.getThrowableMutableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            tryAgain();
        });
        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), loginUserResult -> {
            if (loginUserResult == null) {
                return;
            }
            loadingProgressBar.setVisibility(View.GONE);
            if (loginUserResult.getCode() == 200) {
                Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
//        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
//            }
//            return false;
//        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                try {
                    loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                } catch (Exception e) {
                    Log.d("世界是一个bug", e.toString());
                }
            }
        });
        binding.register.setOnClickListener(v -> {
            // 获取 NavHostFragment
            NavController navController = NavHostFragment.findNavController(this);
            // 执行导航操作
            navController.navigate(R.id.fragment_loginDestination_register);
//            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(binding.register, "register").build();
//            NavHostFragment.findNavController(this).navigate(R.id.fragment_loginDestination_register, null, // Bundle of args
//                    null, // NavOptions
//                    extras);
        });
    }

    private void tryAgain() {

    }


    private void showLoginFailed(@StringRes Integer errorString) {
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(getContext().getApplicationContext(), errorString, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}