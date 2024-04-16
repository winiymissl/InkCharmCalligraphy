package com.example.module_community.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.module_community.R;
import com.example.module_community.databinding.ItemRecyclerviewChoosePicBinding;
import com.example.module_community.ui.adapter.model.ChoosePicItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-16 20:36
 * @Version 1.0
 */
public class PostRecyclerviewAdapter extends RecyclerView.Adapter {
    private List<ChoosePicItem> list = new ArrayList<>();

    public void setList(List<ChoosePicItem> list) {
        int start = this.list.size();
        for (int i = 0; i < list.size(); i++) {
            this.list.add(list.get(i));
            notifyItemInserted(start + i);
        }
    }

    public List<ChoosePicItem> getList() {
        return list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_choose_pic, parent, false);
        ItemRecyclerviewChoosePicBinding binding = ItemRecyclerviewChoosePicBinding.bind(view);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if (list.size() == position) {
            myViewHolder.bindTo(position);
        } else {
            myViewHolder.bindTo(list.get(position));
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemRecyclerviewChoosePicBinding binding;

        public MyViewHolder(@NonNull ItemRecyclerviewChoosePicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(int position) {
            Glide.with(binding.getRoot()).load(com.example.common.R.drawable.add_pic).into(binding.imageViewAdd);

        }

        void bindTo(ChoosePicItem item) {
            if (item.getUrl() != null) {
                Glide.with(binding.getRoot()).load(item.getUrl()).into(binding.imageViewAdd);
            } else {
                Glide.with(binding.getRoot()).load(item.getFile()).into(binding.imageViewAdd);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }
}
