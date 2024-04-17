package com.example.module_community.data.model.request;

/**
 * @Author winiymissl
 * @Date 2024-04-17 18:19
 * @Version 1.0
 */
public class CommentRequest {
    private Integer post_id;
    private Integer page;
    private Integer page_size;

    public CommentRequest(Integer post_id, Integer page, Integer page_size) {
        this.post_id = post_id;
        this.page = page;
        this.page_size = page_size;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPage_size() {
        return page_size;
    }
}
