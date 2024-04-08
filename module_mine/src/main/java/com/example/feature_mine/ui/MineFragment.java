package com.example.feature_mine.ui;

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

import com.example.common.route.RouteConstant;
import com.example.feature_mine.ui.adapter.MineListVIewAdapter;
import com.example.feature_mine.ui.adapter.model.MineListViewModel;
import com.example.feature_mine.ui.viewmodel.MineViewModel;
import com.example.lib_annotation.Route;
import com.example.lib_router_core.template.Router;
import com.example.module_mine.R;
import com.example.module_mine.databinding.FragmentMineBinding;
import com.google.android.material.transition.MaterialFade;


@Route(destinationText = RouteConstant.MINE_FRAGMENT)
public class MineFragment extends Fragment {
    private FragmentMineBinding binding;

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
                Router.getInstance().build(RouteConstant.LOGIN_FRAGMENT).navigation(navController);
            } catch (Exception e) {
                Log.d("世界是一个bug", e.toString());
            }
        });
    }
}