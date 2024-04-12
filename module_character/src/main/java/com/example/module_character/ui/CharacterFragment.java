package com.example.module_character.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.example.common.base.BaseFragment;
import com.example.module_character.R;
import com.example.module_character.databinding.FragmentCharacterBinding;
import com.example.module_character.ui.adapter.RecyclerviewAdapter;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.HeroCarouselStrategy;
import com.google.android.material.carousel.MultiBrowseCarouselStrategy;
import com.google.android.material.transition.MaterialFade;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-10 22:38
 * @Version 1.0
 */
public class CharacterFragment extends BaseFragment<FragmentCharacterBinding> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.bind(inflater.inflate(R.layout.fragment_character, container, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerviewAdapter adapter = new RecyclerviewAdapter();
        List<Integer> list = new ArrayList<>();
        list.add(com.example.common.R.drawable.ic_history);
        list.add(com.example.common.R.drawable.ic_history_2);
//        list.add(com.example.common.R.drawable.ic_shoujin_history);
        adapter.setData(list);
        binding.carouselRecyclerViewHistory.setLayoutManager(new CarouselLayoutManager(new HeroCarouselStrategy()));
        binding.carouselRecyclerViewHistory.setAdapter(adapter);

        RecyclerviewAdapter adapter_recommand = new RecyclerviewAdapter();
        List<Integer> list_recommand = new ArrayList<>();
        list_recommand.add(com.example.common.R.drawable.ic_shoujin_history);
        list_recommand.add(com.example.common.R.drawable.ic_caoshu);
        list_recommand.add(com.example.common.R.drawable.ic_caoshu_recommand);
        adapter_recommand.setData(list_recommand);
        binding.carouselRecyclerViewRecommond.setLayoutManager(new CarouselLayoutManager(new MultiBrowseCarouselStrategy()));
        binding.carouselRecyclerViewRecommond.setAdapter(adapter_recommand);

        binding.textViewScore.setOnClickListener(v -> {
            try {
                NavHostFragment.findNavController(this).navigate(R.id.scoreFragment);
            } catch (Exception e) {
                Log.d("世界是一个bug", e.toString());
            }
        });
    }
}
