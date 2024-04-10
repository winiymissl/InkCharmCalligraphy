package com.example.module_login.data.model.request;

/**
 * @Author winiymissl
 * @Date 2024-04-04 17:17
 * @Version 1.0
 */
public class RegisterUserRequest {
    private String password;
    private String email;
    private String email_code;

    public RegisterUserRequest(String email, String password, String email_code) {
        this.password = password;
        this.email = email;
        this.email_code = email_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_code() {
        return email_code;
    }

    public void setEmail_code(String email_code) {
        this.email_code = email_code;
    }
}
