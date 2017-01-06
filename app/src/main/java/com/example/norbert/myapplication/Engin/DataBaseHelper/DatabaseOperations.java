package com.example.norbert.myapplication.Engin.DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.norbert.myapplication.Engin.Repository.InformationRepository;

/**
 * Created by Kamil Czaja on 2017-01-03.
 * All rights reserved
 */

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;

    public DatabaseOperations(Context context) {
        super(context, InformationRepository.InformationTableDetails.DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INFORMACJE_TABLE = "CREATE TABLE \"Informacje\" ( `waga` INTEGER, `wzrost` INTEGER, `ilosc_kalorii` INTEGER, `poziom_aktywnosci` INTEGER, `tluszcz` INTEGER, `weglowodany` INTEGER, `bialko` INTEGER, `ID` INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE )";
        db.execSQL(CREATE_INFORMACJE_TABLE);

        Log.d("Informacje Created","Informacje");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
