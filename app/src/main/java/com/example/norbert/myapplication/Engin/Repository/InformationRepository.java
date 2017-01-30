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
import com.example.norbert.myapplication.Engin.Objects.UserInformation;


public class InformationRepository{

    public InformationRepository(){}

    public static abstract class InformationTableDetails implements BaseColumns {


        public static final String TABLE_NAME = "Informacje";

        public static final String COLUMN_WAGA = "waga";
        public static final String COLUMN_WZROST = "wzrost";
        public static final String COLUMN_KALORIE = "ilosc_kalorii";
        public static final String COLUMN_POZIOM = "poziom_aktywnosci";
        public static final String COLUMN_TLUSZCZ = "tluszcz";
        public static final String COLUMN_WEGLOWODANY = "weglowodany";
        public static final String COLUMN_BIALKO = "bialko";
        public static final String COLUMN_CEL = "cel";
        public static final String COLUMN_PLEC = "plec";
        public static final String COLUMN_BUDOWA = "budowa_ciala";
        public static final String COLUMN_AGE = "wiek";
        public static final String COLUMN_ID = "ID";
    }


    public void putInformationData(DatabaseOperations db, UserInformation data)
    {
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(InformationTableDetails.COLUMN_WAGA, data.getWeight());
        content.put(InformationTableDetails.COLUMN_WZROST, data.getHeight());
        content.put(InformationTableDetails.COLUMN_KALORIE, data.getCal());
        content.put(InformationTableDetails.COLUMN_POZIOM, data.getActivityLvl());
        content.put(InformationTableDetails.COLUMN_TLUSZCZ, data.getFat());
        content.put(InformationTableDetails.COLUMN_WEGLOWODANY, data.getCarb());
        content.put(InformationTableDetails.COLUMN_BIALKO, data.getProtein());
        content.put(InformationTableDetails.COLUMN_CEL, data.getTarget());
        content.put(InformationTableDetails.COLUMN_PLEC, data.getSex());
        content.put(InformationTableDetails.COLUMN_BUDOWA, data.getBodyType());
        content.put(InformationTableDetails.COLUMN_AGE, data.getAge());


        long success = DB.insert(InformationTableDetails.TABLE_NAME,null,content);
        Log.d("DataBase operations", "Row inserted to informacje");
        DB.close();
    }

        public UserInformation getInformationData(DatabaseOperations db)
        {
            try{
                SQLiteDatabase DB = db.getReadableDatabase();
                String[] coloumns = {InformationTableDetails.COLUMN_WAGA, InformationTableDetails.COLUMN_WZROST,InformationTableDetails.COLUMN_KALORIE,
                        InformationTableDetails.COLUMN_POZIOM,InformationTableDetails.COLUMN_TLUSZCZ,
                        InformationTableDetails.COLUMN_WEGLOWODANY,InformationTableDetails.COLUMN_BIALKO,InformationTableDetails.COLUMN_CEL,InformationTableDetails.COLUMN_PLEC,
                        InformationTableDetails.COLUMN_BUDOWA,InformationTableDetails.COLUMN_AGE,InformationTableDetails.COLUMN_ID};

                Cursor CR = DB.query(InformationTableDetails.TABLE_NAME, coloumns,null,null,null,null,InformationTableDetails.COLUMN_ID+" DESC");

                CR.moveToFirst();
                UserInformation userData= new UserInformation(Float.parseFloat(CR.getString(0)),(Float.parseFloat(CR.getString(1))),
                        (Float.parseFloat(CR.getString(2))),(Float.parseFloat(CR.getString(3))),(Float.parseFloat(CR.getString(4))),
                        (Float.parseFloat(CR.getString(5))),(Float.parseFloat(CR.getString(6))),CR.getString(7),CR.getString(9),
                            Integer.parseInt(CR.getString(8)),Integer.parseInt(CR.getString(10)));

                Log.d("DataBase operations","Data recievied from DB");

                DB.close();
                return userData;
            }
            catch (Exception ex){
                return  new UserInformation();
            }

        }


}

