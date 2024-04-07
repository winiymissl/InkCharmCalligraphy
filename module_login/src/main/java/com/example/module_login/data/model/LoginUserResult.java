package com.example.module_login.data.model;

public class LoginUserResult {

    private Integer code;
    private String message;
    private DataDTO data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        private String accessToken;
        private Integer accessExpire;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Integer getAccessExpire() {
            return accessExpire;
        }

        public void setAccessExpire(Integer accessExpire) {
            this.accessExpire = accessExpire;
        }
    }
}