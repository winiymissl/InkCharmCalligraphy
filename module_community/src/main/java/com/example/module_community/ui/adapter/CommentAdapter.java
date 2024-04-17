package com.example.module_community.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_community.R;
import com.example.module_community.databinding.ItemRecyclerviewCommentBinding;
import com.example.module_community.ui.adapter.model.CommentItem;

import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-17 17:45
 * @Version 1.0
 */
public class CommentAdapter extends RecyclerView.Adapter {
    List<CommentItem> list;

    public void setCommentData(List<CommentItem> list_comment) {
        for (int i = 0; i < list.size(); i++) {
            list.add(list_comment.get(i));
            notifyItemInserted(i);
        }
    }

    public void addComment(CommentItem item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerviewCommentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recyclerview_comment, parent, false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
        commentViewHolder.bindTo(list.get(position));
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        ItemRecyclerviewCommentBinding binding;

        public CommentViewHolder(@NonNull ItemRecyclerviewCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(CommentItem item) {
            binding.setItem(item);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
