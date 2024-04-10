package com.example.common.base;

import com.tencent.mmkv.MMKV;

/**
 * @Author winiymissl
 * @Date 2024-04-07 11:16
 * @Version 1.0
 */
public class MyMMkv {
    public static MMKV getMyDefaultMMkv() {
        MMKV.initialize(BaseApplication.getInstance());
        return MMKV.defaultMMKV();
    }

}
