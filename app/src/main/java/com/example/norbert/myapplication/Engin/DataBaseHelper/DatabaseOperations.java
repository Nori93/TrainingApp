package com.example.norbert.myapplication.Engin.DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.norbert.myapplication.Engin.Repository.InformationRepository;
import com.example.norbert.myapplication.Engin.Repository.SeriesRepository;

/**
 * Created by Kamil Czaja on 2017-01-03.
 * All rights reserved
 */

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;
    public static final String DATABASE_NAME = "cwiczenaia2.7";
    public static final String LOG = "DataBase";


    public DatabaseOperations(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_INFORMACJE_TABLE = "CREATE TABLE \"Informacje\" ( `waga` REAL, `wzrost` REAL, `ilosc_kalorii` INTEGER, `poziom_aktywnosci` TEXT, `tluszcz` INTEGER, `weglowodany` INTEGER, `bialko` INTEGER, `plec` NUMERIC, `budowa_ciala` TEXT, `ID` INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, `cel` TEXT )";
        db.execSQL(CREATE_INFORMACJE_TABLE);
        Log.d(LOG,"Informacje created");
        String CREATE_SERIA_TABLE = "CREATE TABLE \"Seria\" ( `ID` INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, `powtorzenia` INTEGER, `obciazenie` INTEGER, `ID_cw` INTEGER, `ID_tr` INTEGER)";
        db.execSQL(CREATE_SERIA_TABLE);
        Log.d(LOG,"Serie created");
        String CREATE_EXERCISE_TABLE = "CREATE TABLE \"cwiczenia\" ( `ID` INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, `nazwa` TEXT UNIQUE, `opis` TEXT, `instrukcje` TEXT, `path` TEXT )";
        db.execSQL(CREATE_EXERCISE_TABLE);
        Log.d(LOG,"Cwiczenia created");
        String CREATE_TRAINING_TABLE ="CREATE TABLE `trening` ( `ID` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, `data` TEXT NOT NULL )";
        db.execSQL(CREATE_TRAINING_TABLE);
        Log.d(LOG,"Trening created");

        feedDatabase(db);
    }

    private void feedDatabase(SQLiteDatabase db){
        Log.d(LOG,"Running feed method");
        SeedClassHelper.DBSeeder.SeedDataBase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
