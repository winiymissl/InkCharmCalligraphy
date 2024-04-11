package com.example.module_community.ui.adapter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-11 12:47
 * @Version 1.0
 */
public class CommunityItem {
    int image;
    String name;
    String content;

    public CommunityItem(int image, String name, String content) {
        this.image = image;
        this.name = name;
        this.content = content;
    }

    static int[] images = new int[]{
            com.example.common.R.drawable.ic_caoshu,
            com.example.common.R.drawable.ic_caoshu_recommand,
            com.example.common.R.drawable.ic_shoujin_history,

    };
    static String[] names = new String[]{
            "山房",
            "不染",
            "游龙",
    };
    static String[] contents = new String[]{
            "著名草书作品",
            "这个草书很有水准",
            "无敌的瘦金体",
    };

    public static List<CommunityItem> getCommunityItem() {
        List<CommunityItem> list = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            list.add(new CommunityItem(images[i], names[i], contents[i]));
        }
        return list;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
