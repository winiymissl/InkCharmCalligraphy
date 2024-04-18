package com.example.feature_mine.ui.adapter.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 21:19
 * @Version 1.0
 */
public class FansItem {
    public FansItem(Integer user_id, String avatar_background, String nick_name, Integer follow_count, Integer fans_count, String email) {
        this.user_id = user_id;
        this.avatar_background = avatar_background;
        this.nick_name = nick_name;
        this.follow_count = follow_count;
        this.fans_count = fans_count;
        this.email = email;
    }

    private Integer user_id;
    private String avatar_background;
    private String nick_name;
    private Integer follow_count;
    private Integer fans_count;
    private String email;

    public Integer getUser_id() {
        return user_id;
    }

    public String getAvatar_background() {
        return avatar_background;
    }

    public String getNick_name() {
        return nick_name;
    }

    public Integer getFollow_count() {
        return follow_count;
    }

    public Integer getFans_count() {
        return fans_count;
    }

    public String getEmail() {
        return email;
    }
}