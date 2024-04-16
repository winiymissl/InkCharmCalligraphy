package com.example.module_community.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.module_community.ui.adapter.model.CommunityItem;

/**
 * @Author winiymissl
 * @Date 2024-04-15 13:04
 * @Version 1.0
 */
public class PagingComparator extends DiffUtil.ItemCallback<CommunityItem> {
    @Override
    public boolean areItemsTheSame(@NonNull CommunityItem oldItem, @NonNull CommunityItem newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public boolean areContentsTheSame(@NonNull CommunityItem oldItem, @NonNull CommunityItem newItem) {
        return oldItem.getName().equals(newItem.getName());
    }
}
