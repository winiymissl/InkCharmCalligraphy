package com.example.module_community.data.model.request;

/**
 * @Author winiymissl
 * @Date 2024-04-17 22:07
 * @Version 1.0
 */
public class CancelCollectRequest {
    Integer post_id;

    public CancelCollectRequest(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getPost_id() {
        return post_id;
    }
}