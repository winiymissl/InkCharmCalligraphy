package com.example.common.base;

/**
 * @Author winiymissl
 * @Date 2024-04-04 17:20
 * @Version 1.0
 */
public class BaseResult {
    protected int code;
    protected String message;

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
