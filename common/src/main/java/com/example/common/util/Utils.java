package com.example.common.util;

import android.content.Context;

/**
 * @Author winiymissl
 * @Date 2024-04-03 21:27
 * @Version 1.0
 */
public class Utils {
    private static Context context;

    private Utils() {
        /*
        我就不准你实例化我
         */
        throw new UnsupportedOperationException("u can't instantiate me...damn");
    }


    public static void init(Context context) {
        Utils.context = context.getApplicationContext();

    }

    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }

    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }
}
