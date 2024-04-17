package com.example.module_community.ui.adapter.databindingattr;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.module_community.ui.adapter.ImageAdapter;
import com.google.android.material.imageview.ShapeableImageView;
import com.youth.banner.Banner;

import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-12 21:10
 * @Version 1.0
 */
public class CustomAttr {
    @BindingAdapter("imageUrl")
    public static void setImage(ShapeableImageView customView, String imageUrl) {
        if (imageUrl == null) {
            Glide.with(customView.getContext()).load(com.example.common.R.drawable.ic_caoshu).into(customView);
        } else {
            Glide.with(customView.getContext()).load(imageUrl).into(customView);
        }
    }

    @BindingAdapter("bannerImageUrl")
    public static void setBannerImage(Banner customView, List<String> imageUrl) {
        if (imageUrl == null) {
        } else {
            customView.setAdapter(new ImageAdapter(imageUrl));
        }
    }
}
