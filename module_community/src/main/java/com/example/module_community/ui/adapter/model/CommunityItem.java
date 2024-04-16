package com.example.module_community.ui.adapter.model;

/**
 * @Author winiymissl
 * @Date 2024-04-11 12:47
 * @Version 1.0
 */
public class CommunityItem {
    String image;
    String name;
    String content;
    String user_avatar;

    @Override
    public String toString() {
        return "CommunityItem{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", user_avatar='" + user_avatar + '\'' +
                '}';
    }

    public CommunityItem(String image, String name, String content, String user_avatar) {
        this.image = image;
        this.name = name;
        this.content = content;
        this.user_avatar = user_avatar;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
