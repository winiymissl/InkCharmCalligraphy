package com.example.module_community.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.transition.Explode;

import com.example.common.base.BaseFragment;
import com.example.module_community.R;
import com.example.module_community.databinding.FragmentCommunityDetailBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.wx.goodview.GoodView;

/**
 * @Author winiymissl
 * @Date 2024-04-12 0:02
 * @Version 1.0
 */
public class CommunityDetailFragment extends BaseFragment<FragmentCommunityDetailBinding> {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new Explode());
        setExitTransition(new Explode());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_detail, container, false);
        binding = FragmentCommunityDetailBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        binding.icon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                Chip c = (Chip) buttonView;
//                if (isChecked) {
//                    c.setChipIcon(getResources().getDrawable(com.example.common.R.drawable.ic_like_red));
//                    GoodView goodView = new GoodView(getHoldingsActivity());
//                    goodView.setText("+1");
//                    goodView.show(c);
//                } else {
//                    c.setChipIcon(getResources().getDrawable(com.example.common.R.drawable.ic_like));
//                    GoodView goodView = new GoodView(getHoldingsActivity());
//                    goodView.setText("-1");
//                    goodView.show(c);
//                }
//            }
//        });
//        binding.icon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                MaterialCheckBox c = (MaterialCheckBox) buttonView;
//                if (isChecked) {
//                    c.set(getResources().getDrawable(com.example.common.R.drawable.ic_like_red));
//                    GoodView goodView = new GoodView(getHoldingsActivity());
//                    goodView.setText("+1");
//                    goodView.show(c);
//                } else {
//                    c.setChipIcon(getResources().getDrawable(com.example.common.R.drawable.ic_like));
//                    GoodView goodView = new GoodView(getHoldingsActivity());
//                    goodView.setText("-1");
//                    goodView.show(c);
//                }
//
//            }
//        });
        binding.iconButtonCollect.setOnClickListener(v -> {
            GoodView goodView = new GoodView(getHoldingsActivity());
            goodView.setImage(com.example.common.R.drawable.ic_collect_yellow);
            goodView.setText("已收藏");
            goodView.show(v);
        });
        binding.iconButtonShare.setOnClickListener(v -> {
            /*
             * 分享的dialog
             * */
            NavHostFragment.findNavController(this).navigate(R.id.shareDialogFragment);
        });

        binding.iconButtonCommon.addOnCheckedChangeListener(new MaterialButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MaterialButton button, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });
        binding.fabBack.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });
    }
}
