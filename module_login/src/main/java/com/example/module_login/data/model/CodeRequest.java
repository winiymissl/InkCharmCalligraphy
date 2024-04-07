package com.example.module_login.data.model;

/**
 * @Author winiymissl
 * @Date 2024-04-04 20:33
 * @Version 1.0
 */
public class CodeRequest {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CodeRequest(String email) {
        this.email = email;
    }
}
