package com.example.module_community.data.model.request;

/**
 * @Author winiymissl
 * @Date 2024-04-18 18:44
 * @Version 1.0
 */
public class CancelFollowRequest {
    private Integer user_id;

    public CancelFollowRequest(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
    }
}
