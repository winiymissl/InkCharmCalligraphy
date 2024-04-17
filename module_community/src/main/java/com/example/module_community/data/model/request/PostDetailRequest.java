package com.example.module_community.data.model.request;

/**
 * @Author winiymissl
 * @Date 2024-04-17 13:07
 * @Version 1.0
 */
public class PostDetailRequest {
    Integer post_id;

    public PostDetailRequest(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getPost_id() {
        return post_id;
    }
}
