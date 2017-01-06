package com.example.norbert.myapplication.Engin.Repository;

/**
 * Created by Kamil Czaja on 2017-01-03.
 * All rights reserved
 */


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;


public class InformationRepository{

    public InformationRepository(){}

    public static abstract class InformationTableDetails implements BaseColumns {

        public static final String DATABASE_NAME = "test1234";
        public static final String TABLE_NAME = "Informacje";

        public static final String COLUMN_WAGA = "waga";
        public static final String COLUMN_WZROST = "wzrost";
        public static final String COLUMN_KALORIE = "ilosc_kalorii";
        public static final String COLUMN_POZIOM = "poziom_aktywnosci";
        public static final String COLUMN_TLUSZCZ = "tluszcz";
        public static final String COLUMN_WEGLOWODANY = "weglowodany";
        public static final String COLUMN_BIALKO = "bialko";
    }


    public void PutInformationData (DatabaseOperations db, int data)
    {
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(InformationTableDetails.COLUMN_WAGA,data);
        content.put(InformationTableDetails.COLUMN_WZROST, 185);

        long success = DB.insert(InformationTableDetails.TABLE_NAME,null,content);
        Log.d("DataBase operations", "Row inserted to informacje");
    }

        public Cursor getInformation(DatabaseOperations db)
        {
            SQLiteDatabase DB = db.getReadableDatabase();
            String[] coloumns = {InformationTableDetails.COLUMN_WAGA, InformationTableDetails.COLUMN_WZROST};
            Cursor CR = DB.query(InformationTableDetails.TABLE_NAME, coloumns,null,null,null,null,null);

            return CR;
        }


}

