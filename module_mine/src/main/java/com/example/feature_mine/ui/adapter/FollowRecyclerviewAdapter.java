package com.example.feature_mine.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feature_mine.ui.adapter.model.FollowItem;
import com.example.module_mine.R;
import com.example.module_mine.databinding.ItemRecyclerviewFollowBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 21:20
 * @Version 1.0
 */
public class FollowRecyclerviewAdapter extends RecyclerView.Adapter {
    List<FollowItem> list = new ArrayList<>();

    public void setData(List<FollowItem> result) {
        list.clear();
        for (int i = 0; i < result.size(); i++) {
            list.add(result.get(i));
            notifyItemInserted(i);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_follow, parent, false);
        ItemRecyclerviewFollowBinding binding = ItemRecyclerviewFollowBinding.bind(view);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.bindTo(list.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemRecyclerviewFollowBinding binding;

        public MyViewHolder(@NonNull ItemRecyclerviewFollowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(FollowItem item) {
            binding.setItem(item);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
