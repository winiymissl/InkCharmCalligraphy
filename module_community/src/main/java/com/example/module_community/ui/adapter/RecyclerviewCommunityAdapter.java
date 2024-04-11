package com.example.module_community.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_community.R;
import com.example.module_community.databinding.ItemRecyclerviewCommunityBinding;
import com.example.module_community.ui.adapter.model.CommunityItem;

import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-11 12:46
 * @Version 1.0
 */
public class RecyclerviewCommunityAdapter extends RecyclerView.Adapter {
    List<CommunityItem> list;

    public void setData(List<CommunityItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_community, parent, false);
        ItemRecyclerviewCommunityBinding binding = ItemRecyclerviewCommunityBinding.bind(view);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemRecyclerviewCommunityBinding binding;

        public MyViewHolder(@NonNull ItemRecyclerviewCommunityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setData(CommunityItem item) {
            binding.shapeableImageView.setImageResource(item.getImage());
            binding.textViewContent.setText(item.getContent());
            binding.textViewName.setText(item.getName());
        }
    }
}
