package com.example.feature_mine.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.common.base.BaseFragment;
import com.example.feature_mine.ui.adapter.MineListVIewAdapter;
import com.example.feature_mine.ui.adapter.model.MineListViewModel;
import com.example.feature_mine.ui.viewmodel.MineViewModel;
import com.example.module_mine.R;
import com.example.module_mine.databinding.FragmentMineBinding;
import com.google.android.material.transition.MaterialFade;


public class MineFragment extends BaseFragment<FragmentMineBinding> {

    private MineViewModel mViewModel;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        if (BuildConfig.DEBUG) {
//        ARouter.openLog();
//        ARouter.openDebug();
//        }
//        ARouter.init(getActivity().getApplication());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
        setReturnTransition(new MaterialFade());
        setReenterTransition(new MaterialFade());
        binding = FragmentMineBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, container, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
        MineListVIewAdapter adapter = new MineListVIewAdapter(MineListViewModel.getData());
        NavController navController = NavHostFragment.findNavController(this);
        binding.includeLogin.listViewMine.setAdapter(adapter);
        binding.includeLogin.listViewMine.setOnItemClickListener((parent, view1, position, id) -> {
            if (position == 0) {
                navController.navigate(R.id.fragment_mineDestination_setting_fragment);
            }
        });
        
        binding.includeNotLogin.buttonToLogin.setOnClickListener(v -> {
            try {
//                ARouter.getInstance().build("/module_login/ui/LoginActivity").navigation();
//                Router.getInstance().build(RouteConstant.LOGIN_FRAGMENT).navigation(navController);
                Class clazz = Class.forName("com.example.module_login.ui.LoginActivity");
                Intent intent = new Intent(getActivity(), clazz);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            } catch (Exception e) {
                Log.d("世界是一个bug", e.toString());
            }
        });


    }
}