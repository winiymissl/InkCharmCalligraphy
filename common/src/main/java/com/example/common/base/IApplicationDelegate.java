package com.example.common.base;

/**
 * @Author winiymissl
 * @Date 2024-04-03 21:22
 * @Version 1.0
 */
public interface IApplicationDelegate {
    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onTrimMemory(int level);
}
