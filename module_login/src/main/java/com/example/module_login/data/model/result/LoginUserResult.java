package com.example.module_login.data.model.result;

import com.example.common.base.BaseResult;

public class LoginUserResult extends BaseResult {

    private DataDTO data;


    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        private String access_token;
        private Integer access_expire;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public Integer getAccess_expire() {
            return access_expire;
        }

        public void setAccess_expire(Integer access_expire) {
            this.access_expire = access_expire;
        }
    }
}