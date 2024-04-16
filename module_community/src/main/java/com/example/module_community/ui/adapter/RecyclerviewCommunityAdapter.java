package com.example.module_community.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_community.R;
import com.example.module_community.data.model.result.CommunityInfoResult;
import com.example.module_community.databinding.ItemRecyclerviewCommunityBinding;
import com.example.module_community.ui.adapter.model.CommunityItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-11 12:46
 * @Version 1.0
 */
public class RecyclerviewCommunityAdapter extends RecyclerView.Adapter {
    //    public RecyclerviewCommunityAdapter(@NonNull DiffUtil.ItemCallback diffCallback, @NonNull CoroutineContext mainDispatcher) {
//        super(diffCallback, mainDispatcher);
//    }
//
//    public RecyclerviewCommunityAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
//        super(diffCallback);
//    }
//
//    public RecyclerviewCommunityAdapter(@NonNull DiffUtil.ItemCallback diffCallback, @NonNull CoroutineContext mainDispatcher, @NonNull CoroutineContext workerDispatcher) {
//        super(diffCallback, mainDispatcher, workerDispatcher);
//    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public RecyclerviewCommunityAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    List<CommunityItem> list = new ArrayList<>();

    public void setData(CommunityInfoResult result) {
        try {
            List<CommunityItem> itemList = net2ui(result);
            int curPage = result.getData().getCurrent_page();
            int total = result.getData().getTotal_page();
            Log.d("世界是一个bug", " curPage  " + curPage);
            Log.d("世界是一个bug", " total  " + total);
            if (curPage == 1) {
                list.clear();
                for (int i = 0; i < itemList.size(); i++) {
                    list.add(itemList.get(i));
                    notifyItemInserted(i);
                }
            } else {
                int start = list.size();
                for (int i = 0; i < itemList.size(); i++) {
                    list.add(itemList.get(i));
                    notifyItemInserted(start - 1 + i);
                }
            }
        } catch (Exception e) {
            Log.d("世界是一个bug", e.toString());
        }
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
        myViewHolder.onClick(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemRecyclerviewCommunityBinding binding;

        public MyViewHolder(@NonNull ItemRecyclerviewCommunityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setData(CommunityItem item) {
            binding.setItem(item);
//            Glide.with(binding.getRoot().getContext()).load(item.getImage()).into(binding.shapeableImageView);
//            binding.shapeableImageView.setImageResource(item.getImage());
//            binding.textViewContent.setText(item.getContent());
//            binding.textViewName.setText("来自  " + item.getName());
        }

        void onClick(int position) {
            binding.getRoot().setOnClickListener(v -> {
                onItemClickListener.onItemClick(v, position);
            });
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    private List<CommunityItem> net2ui(CommunityInfoResult result) {
        List<CommunityItem> list = new ArrayList<>();
//        if (result.getData().getPost_data() != null) {
        result.getData().getPost_data().forEach(postDataDTO -> {
            list.add(new CommunityItem(postDataDTO.getImage_urls().get(0), postDataDTO.getUser_info().getNick_name(), postDataDTO.getContent(), postDataDTO.getUser_info().getAvatar_image()));
        });
//        }
        return list;
    }
}
