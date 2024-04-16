package com.example.common.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * @Author winiymissl
 * @Date 2024-04-03 21:27
 * @Version 1.0
 */
public class Utils {
    public static void tryAgain(String message, Context context) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle("提示").setMessage(message);
        builder.setNeutralButton("close", (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static String getFilePathFromUri(Context context, Uri uri) {
        String filePath = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            filePath = cursor.getString(columnIndex);
            cursor.close();
        }
        return filePath;
    }
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
