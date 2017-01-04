package com.example.norbert.myapplication.Engin.DataBaseHelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kamil on 2017-01-03.
 */

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    public DatabaseOperations(Context context) {
        super(context, InformationRepository.InformationInfo.DATABASE_NAME, null, DB_VERSION);
    }

    public DatabaseOperations(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
