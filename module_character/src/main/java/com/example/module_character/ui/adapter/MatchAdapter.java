package com.example.module_character.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_character.R;
import com.example.module_character.databinding.ItemRecyclerviewMatchBinding;
import com.example.module_character.ui.adapter.model.MatchItem;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 11:05
 * @Version 1.0
 */
public class MatchAdapter extends RecyclerView.Adapter {
    List<MatchItem> list = new ArrayList<>();

    public void setData(List<MatchItem> lists) {
        for (int i = 0; i < lists.size(); i++) {
            list.add(lists.get(i));
            notifyItemInserted(i);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_match, parent, false);
        ItemRecyclerviewMatchBinding binding = ItemRecyclerviewMatchBinding.bind(view);
        return new MyViewHolder(binding);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemRecyclerviewMatchBinding binding;

        public MyViewHolder(@NonNull ItemRecyclerviewMatchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(MatchItem item) {
            binding.btnSignIn.setOnCheckedChangeListener((buttonView, isChecked) -> {
                Chip chip = (Chip) buttonView;
                if (isChecked) {
                    chip.setText("已报名");
                } else {
                    chip.setText("报名");
                }
            });
            binding.setItem(item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.bindTo(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
