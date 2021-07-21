package com.example.prueba3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBAdmin extends SQLiteOpenHelper {

    private static final String TAG = "TAG_DBA";

    public DBAdmin(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(name text primary key, password text)");
        db.execSQL("create table events(event_id integer primary key, title text, date date, " +
                "importance boolean, obs text, location text, alert date, owner text)");
        Log.d(TAG, "onCreate: CREACION DE DB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
