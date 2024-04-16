package com.example.module_character.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
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
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public OnItemClickListener onItemClickListener;

    public RecyclerviewAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    List<Integer> list;

    public void setData(List<Integer> list) {
        this.list = list;
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
        myViewHolder.onClick(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemRecyclerviewShufaBinding binding;

        public MyViewHolder(@NonNull ItemRecyclerviewShufaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Integer integer) {
            ViewCompat.setTransitionName(binding.carouselImageView,"shared_imageView");
            Glide.with(binding.getRoot()).load(integer).into(binding.carouselImageView);
        }

        void onClick(int position) {
            binding.getRoot().setOnClickListener(view -> {
                onItemClickListener.onItemClick(view, position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}
