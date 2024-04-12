package com.example.feature_mine.data;

/**
 * @Author winiymissl
 * @Date 2024-04-12 14:40
 * @Version 1.0
 */
public class Result {
    public static class Success<T> extends Result {
        T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public static class Error<T> extends Result {
        T data;

        public Error(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

}