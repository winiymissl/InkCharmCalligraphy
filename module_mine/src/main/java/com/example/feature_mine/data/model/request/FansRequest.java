package com.example.feature_mine.data.model.request;

/**
 * @Author winiymissl
 * @Date 2024-04-18 22:01
 * @Version 1.0
 */
public class FansRequest {
    int page;
    int page_size;

    public FansRequest(int page, int page_size) {
        this.page = page;
        this.page_size = page_size;
    }

    public int getPage() {
        return page;
    }

    public int getPage_size() {
        return page_size;
    }
}
