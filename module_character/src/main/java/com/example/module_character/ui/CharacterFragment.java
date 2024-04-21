package com.example.module_character.ui;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.common.base.BaseFragment;
import com.example.common.base.MyMMkv;
import com.example.common.util.Utils;
import com.example.module_character.R;
import com.example.module_character.databinding.FragmentCharacterBinding;
import com.example.module_character.ui.adapter.RecyclerviewAdapter;
import com.example.module_character.ui.viewmodel.CharacterViewModel;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.HeroCarouselStrategy;
import com.google.android.material.transition.MaterialFade;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-10 22:38
 * @Version 1.0
 */
public class CharacterFragment extends BaseFragment<FragmentCharacterBinding> {

    private CharacterViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
        setSharedElementEnterTransition(new ChangeBounds());
        setSharedElementReturnTransition(new ChangeBounds());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.bind(inflater.inflate(R.layout.fragment_character, container, false));
        return binding.getRoot();
    }

    private void init() {
        if (MyMMkv.getMyDefaultMMkv().getString("token", null) == null) {
            binding.textViewUnlogin.setVisibility(View.VISIBLE);
            binding.carouselRecyclerViewHistory.setVisibility(View.INVISIBLE);
        }
    }

    private boolean isLogin() {
        return MyMMkv.getMyDefaultMMkv().getString("token", null) != null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        mViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
//        ShapeableImageView shapeableImageView = (ShapeableImageView) view1.findViewById(R.id.carousel_image_view);
//        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(shapeableImageView, shapeableImageView.getTransitionName()).build();
//        NavHostFragment.findNavController(this).navigate(R.id.enterViewFragment, null, null, extras);
        mViewModel.fetchSentence("i");
        mViewModel.getmSentenceMutableLiveData().observe(getViewLifecycleOwner(), sentenceResult -> {
            if (sentenceResult == null) {
                return;
            }
            binding.textviewSentence.setText(sentenceResult.getHitokoto() + "\n  ——" + sentenceResult.getFrom());
        });
        mViewModel.getThrowableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            Utils.tryAgain(throwable.getMessage(), getActivity());
        });
        binding.buttonOtherOne.setOnClickListener(v -> {
            mViewModel.fetchSentence("f");
        });
        RecyclerviewAdapter adapter = new RecyclerviewAdapter();
        List<Integer> list = new ArrayList<>();
        list.add(com.example.common.R.drawable.ic_history);
        list.add(com.example.common.R.drawable.ic_history_2);
        list.add(com.example.common.R.drawable.ic_shoujin_history);
        list.add(com.example.common.R.drawable.ic_shoujin_history);
        list.add(com.example.common.R.drawable.ic_caoshu);
        list.add(com.example.common.R.drawable.ic_caoshu_recommand);
        adapter.setData(list);
        binding.carouselRecyclerViewHistory.setLayoutManager(new CarouselLayoutManager(new HeroCarouselStrategy()));
        binding.carouselRecyclerViewHistory.setAdapter(adapter);


        BannerImageAdapter imageAdapter = new BannerImageAdapter(list) {

            @Override
            public void onBindView(Object holder, Object data, int position, int size) {
                BannerImageHolder myHolder = (BannerImageHolder) holder;
                myHolder.imageView.setImageResource(list.get(position));
            }

        };
        binding.banner.setAdapter(imageAdapter);
        binding.banner.addPageTransformer(new ZoomOutPageTransformer());
        binding.banner.addPageTransformer(new DepthPageTransformer(2));
        binding.banner.setIndicator(new CircleIndicator(getActivity()));

        binding.buttonRecognise.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.TFragment);
        });

        binding.textViewScore.setOnClickListener(v -> {
            if (isLogin()) {
                NavHostFragment.findNavController(this).navigate(R.id.scoreFragment);
            } else {
                Utils.tryAgain("请先登录", getHoldingsActivity());
            }
        });
        binding.textViewCharacter.setOnClickListener(v -> {
            if (isLogin()) {
                NavHostFragment.findNavController(this).navigate(R.id.collectCharacterFragment);
            } else {
                Utils.tryAgain("请先登录", getHoldingsActivity());
            }
        });
        binding.chipSmartScore.setOnClickListener(v -> {
            if (isLogin()) {
                NavHostFragment.findNavController(this).navigate(R.id.historyScoreFragment);
            } else {
                Utils.tryAgain("请先登录", getHoldingsActivity());
            }
        });
        binding.buttonMatch.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.matchFragment);
        });

        binding.btnCheckIn.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.checkInFragment);
        });
    }
}
