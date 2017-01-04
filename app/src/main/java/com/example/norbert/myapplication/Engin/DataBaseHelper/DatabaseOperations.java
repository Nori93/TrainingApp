package com.example.norbert.myapplication.Engin.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kamil Czaja on 2017-01-03.
 * All rights reserved
 */

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    public DatabaseOperations(Context context) {
        super(context, InformationRepository.InformationInfo.DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INFORMACJE_TABLE = "CREATE TABLE \"Informacje\" ( `waga` INTEGER, `wzrost` INTEGER, `ilosc_kalorii` INTEGER, `poziom_aktywnosci` INTEGER, `tluszcz` INTEGER, `weglowodany` INTEGER, `bialko` INTEGER )";
        db.execSQL(CREATE_INFORMACJE_TABLE);

        Log.d("Informacje Created","Informacje");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void PutInformationData (DatabaseOperations db, int data)
    {
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(InformationRepository.InformationInfo.COLUMN_WAGA,data);
        content.put(InformationRepository.InformationInfo.COLUMN_WZROST, 185);

        long success = DB.insert(InformationRepository.InformationInfo.TABLE_NAME,null,content);
        Log.d("DataBase operations", "Row inserted to informacje");
    }


}
