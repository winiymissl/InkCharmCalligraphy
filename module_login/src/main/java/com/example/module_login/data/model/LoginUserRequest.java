package com.example.module_login.data.model;

/**
 * @Author winiymissl
 * @Date 2024-04-04 17:20
 * @Version 1.0
 */
public class LoginUserRequest {

    private String email;
    private String password;

    public LoginUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
