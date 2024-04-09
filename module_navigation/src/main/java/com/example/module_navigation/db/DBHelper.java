package com.example.module_navigation.db;

import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.module_navigation.DebugApplication;

/**
 * @Author winiymissl
 * @Date 2024-04-09 22:05
 * @Version 1.0
 */
public class DBHelper {
    private static final String TAG = "DBHelper";
    private static final String DB_PREFIX = "my_app_info";

    private static final AppDataBase dataBase = Room.databaseBuilder(DebugApplication.getApplication(), AppDataBase.class, DB_PREFIX)
            .addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    Log.d(TAG, "Room database onCreate in thread " + Thread.currentThread().getName());
                }

                @Override
                public void onOpen(SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    Log.d(TAG, "Room database onOpen in thread " + Thread.currentThread().getName());
                }
            })
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build();

    public static AppDataBase getDb() {
        return dataBase;
    }
}
