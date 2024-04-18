package com.example.feature_mine.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feature_mine.ui.adapter.model.FansItem;
import com.example.module_mine.R;
import com.example.module_mine.databinding.ItemRecyclerviewFansBinding;

import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 21:18
 * @Version 1.0
 */

public class FansReceyclerviewAdapter extends RecyclerView.Adapter {
    List<FansItem> list;

    public void setData(List<FansItem> result) {
        for (int i = 0; i < result.size(); i++) {
            list.add(result.get(i));
            notifyItemInserted(i);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_fans, parent, false);
        ItemRecyclerviewFansBinding binding = ItemRecyclerviewFansBinding.bind(view);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.bindTo(list.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemRecyclerviewFansBinding binding;

        public MyViewHolder(@NonNull ItemRecyclerviewFansBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(FansItem item) {

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
