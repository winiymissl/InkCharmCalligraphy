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
            com.example.common.R.drawable.ic_shufa_xinian,
    };
    static String[] names = new String[]{
            "山房",
            "不染",
            "游龙",
            "驹君"
    };
    static String[] contents = new String[]{
            "一幅优秀的书法作品应该具有端庄大气的风采，独特的个性和鲜明的艺术风格。评价书法作品需要审美眼光和文化修养，同时也需要欣赏者对艺术家的用心与情感有所感悟",
            "这个草书很有水准",
            "无敌的瘦金体",
            "我的信念",
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
