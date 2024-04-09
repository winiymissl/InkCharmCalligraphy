package com.example.common.eventbus;

/**
 * @Author winiymissl
 * @Date 2024-04-09 20:11
 * @Version 1.0
 */
public class Event<T> {
    private int code;
    private T data;


    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
