package com.example.feature_mine.ui.adapter.databindingattr;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

/**
 * @Author winiymissl
 * @Date 2024-03-22 22:50
 * @Version 1.0
 */
public class CustomBindingAdapter {
    @BindingAdapter("shapeImageUrl")
    public static void loadShapeImage(ShapeableImageView imageView, String imageUrl) {
        if (imageUrl == null) {
            imageView.setImageResource(com.example.common.R.drawable.ic_avatar);
        } else {
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
        }
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        if (imageUrl == null) {
            imageView.setImageResource(com.example.common.R.drawable.ic_samples);
        } else {
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
        }
    }
}