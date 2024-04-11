package com.example.module_character.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.module_character.R;
import com.example.module_character.databinding.ItemRecyclerviewShufaBinding;

import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-11 0:44ersion 1.0
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter {
    List<Integer> list;

    public void setData(List<Integer> list) {
        this.list = list;
        Log.d("世界是一个bug", "运行到 list ");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_shufa, parent, false);
        ItemRecyclerviewShufaBinding binding = ItemRecyclerviewShufaBinding.bind(view);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.bind(list.get(position));
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemRecyclerviewShufaBinding binding;

        public MyViewHolder(@NonNull ItemRecyclerviewShufaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Integer integer) {
            Log.d("世界是一个bug", "运行到 Glide ");
//            binding.carouselImageView.setImageResource(com.example.common.R.drawable.ic_shufa2);
            Glide.with(binding.getRoot()).load(integer).into(binding.carouselImageView);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}
