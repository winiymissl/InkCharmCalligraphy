package com.example.feature_mine.ui.adapter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-05 17:40
 * @Version 1.0
 */
public class MineListViewEntity {
    int icon;
    String title;
    int next;

    public MineListViewEntity(int icon, String title, int next) {
        this.icon = icon;
        this.title = title;
        this.next = next;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    static int[] icons = new int[]{
            com.example.common.R.drawable.ic_setting,
            com.example.common.R.drawable.logout,
    };
    static String[] titles = new String[]{
            "设置",
            "退出登录"
    };
    static int[] nexts = new int[]{
            com.example.common.R.drawable.ic_right,
            com.example.common.R.drawable.ic_right,

    };

    public static List<MineListViewEntity> getData() {
        List<MineListViewEntity> list = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            list.add(new MineListViewEntity(icons[i], titles[i], nexts[i]));
        }
        return list;
    }
}
