package com.example.module_community.data.model.request;

/**
 * @Author winiymissl
 * @Date 2024-04-12 21:35
 * @Version 1.0
 */
public class CommunityInfoRequest {
    private int page;
    private    int page_size;

    public CommunityInfoRequest(int page, int page_size) {
        this.page = page;
        this.page_size = page_size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }
}
