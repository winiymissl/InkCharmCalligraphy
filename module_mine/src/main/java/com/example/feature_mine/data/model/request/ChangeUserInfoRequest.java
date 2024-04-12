package com.example.feature_mine.data.model.request;

/**
 * @Author winiymissl
 * @Date 2024-04-12 13:39
 * @Version 1.0
 */
public class ChangeUserInfoRequest {
    private String nick_name;
    private String phone;

    public ChangeUserInfoRequest(String nick_name) {
        this.nick_name = nick_name;
    }

    public ChangeUserInfoRequest(String nick_name, String phone) {
        this.nick_name = nick_name;
        this.phone = phone;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
