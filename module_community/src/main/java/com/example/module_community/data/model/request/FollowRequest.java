package com.example.module_community.data.model.request;

/**
 * @Author winiymissl
 * @Date 2024-04-17 22:05
 * @Version 1.0
 */
public class FollowRequest {
    private Integer user_id;

    public FollowRequest(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
    }
}
