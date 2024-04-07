package com.example.feature_mine.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.feature_mine.ui.adapter.model.MineListViewModel;
import com.example.module_mine.R;
import com.example.module_mine.databinding.ItemMineBinding;

import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-05 17:38
 * @Version 1.0
 */
public class MineListVIewAdapter extends BaseAdapter {

    public MineListVIewAdapter(List<MineListViewModel> mineList) {
        mMineList = mineList;
    }

    List<MineListViewModel> mMineList;

    @Override
    public int getCount() {
        return mMineList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMineList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine, parent, false);
        } else {
            view = convertView;
        }
        ItemMineBinding binding = ItemMineBinding.bind(view);
        binding.imageViewIcon.setImageResource(mMineList.get(position).getIcon());
        binding.textViewTitle.setText(mMineList.get(position).getTitle());
        binding.imageViewNext.setImageResource(mMineList.get(position).getNext());
        return view;
    }
}
