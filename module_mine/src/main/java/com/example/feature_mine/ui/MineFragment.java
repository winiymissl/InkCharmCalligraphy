package com.example.feature_mine.ui;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.common.eventbus.BindEventBus;
import com.example.common.eventbus.Event;
import com.example.common.eventbus.EventCode;
import com.example.feature_mine.ui.adapter.MineListVIewAdapter;
import com.example.feature_mine.ui.adapter.model.MineListViewModel;
import com.example.feature_mine.ui.viewmodel.MineViewModel;
import com.example.module_mine.R;
import com.example.module_mine.databinding.FragmentMineBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.transition.MaterialFade;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;

@BindEventBus
public class MineFragment extends BaseFragment<FragmentMineBinding> {

    private MineViewModel mViewModel;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    /**
     * 被自动调用处理事件
     */
    @Subscribe
    public void onMessageEvent(Event event) {
        // 处理收到的事件
        if (EventCode.LoginSuccess == event.getCode()) {
            mViewModel.fetchUserInfoRemoteData(MyMMkv.getMyDefaultMMkv().getString("token", null));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
        binding = FragmentMineBinding.bind(LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, container, false));
        return binding.getRoot();
    }

    /**
     * ARouter.getInstance().build("/module_login/ui/LoginActivity").navigation();
     * Router.getInstance().build(RouteConstant.LOGIN_FRAGMENT).navigation(navController);
     */
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
            } else if (position == 1) {
                new MaterialAlertDialogBuilder(getActivity()).setTitle("退出登录").setMessage("是否退出登录").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            /*
                             * 删除登录的信息
                             * 1. 删除token
                             * 2. 在viewModel中将数据修改掉
                             * 3. 在数据库中删除数据
                             * */
                            mViewModel.delUserInfo();
                            /*
                             * 跳转到登录界面
                             * */
                            Class clazz = Class.forName("com.example.module_login.ui.LoginActivity");
                            Intent intent = new Intent(getActivity(), clazz);
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                        } catch (ClassNotFoundException e) {
                            Log.d("世界是一个bug", e.toString());
                        }
                    }
                }).create().show();
            }
        });
        binding.smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        binding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (MyMMkv.getMyDefaultMMkv().getString("token", null) != null) {
                    Log.d("世界是一个bug", MyMMkv.getMyDefaultMMkv().getString("token", null));
                    mViewModel.fetchUserInfoRemoteData(MyMMkv.getMyDefaultMMkv().getString("token", null));
                } else {
                    binding.smartRefreshLayout.finishRefresh();
                    Toast.makeText(mActivity, "token == null", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.includeNotLogin.buttonToLogin.setOnClickListener(v -> {
            try {
                Class clazz = Class.forName("com.example.module_login.ui.LoginActivity");
                Intent intent = new Intent(getActivity(), clazz);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            } catch (Exception e) {
                Log.d("世界是一个bug", e.toString());
            }
        });
        mViewModel.getUserInfoResult().observe(getViewLifecycleOwner(), userInfoResult -> {
            /*
             * 通过dataBinding，将数据设置到页面，会因为是否为空，而设置到页面上
             * */
            binding.setItem(userInfoResult);
        });
        mViewModel.getThrowableMutableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            binding.smartRefreshLayout.finishRefresh();
            tryAgain(throwable.toString());
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