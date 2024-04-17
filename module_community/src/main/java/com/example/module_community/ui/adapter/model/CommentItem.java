package com.example.module_community.ui.adapter.model;

/**
 * @Author winiymissl
 * @Date 2024-04-17 17:56
 * @Version 1.0
 */
public class CommentItem {
    private String url;

    private String time;
    private String content;
    private String nickName;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public CommentItem(String url, String time, String content, String nickName) {
        this.url = url;
        this.time = time;
        this.content = content;
        this.nickName = nickName;
    }

    public String getUrl() {
        return url;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getNickName() {
        return nickName;
    }
}
