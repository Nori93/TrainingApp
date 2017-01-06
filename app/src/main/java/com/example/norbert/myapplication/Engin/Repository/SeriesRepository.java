package com.example.norbert.myapplication.Engin.Repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.Objects.Series;
import com.example.norbert.myapplication.Engin.Objects.Series;

/**
 * Created by Krzysztof Bzoma on 06.01.2017.
 */

public class SeriesRepository {
    public SeriesRepository(){}

    public static abstract class SeriesTableDetails implements BaseColumns {

        public static final String DATABASE_NAME = "cwiczenia";
        public static final String TABLE_NAME = "seria";

        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_POWTORZENIA = "powtorzenia";
        public static final String COLUMN_OBCIAZENIE = "obciazenie";
        public static final String COLUMN_ID_CW = "ID_cw";
        public static final String COLUMN_ID_TR = "ID_tr";
    }


    public void putInformationData(DatabaseOperations db, Series data)
    {
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(SeriesRepository.SeriesTableDetails.COLUMN_ID, data.getId());
        content.put(SeriesTableDetails.COLUMN_POWTORZENIA, data.getRepeats());
        content.put(SeriesTableDetails.COLUMN_OBCIAZENIE, data.getWeights());
        content.put(SeriesTableDetails.COLUMN_ID_CW, data.getId_cw());
        content.put(SeriesTableDetails.COLUMN_ID_TR, data.getId_tr());


        long success = DB.insert(SeriesRepository.SeriesTableDetails.TABLE_NAME,null,content);
        Log.d("DataBase operations", "Row inserted to series");
    }

    public Series getInformationData(DatabaseOperations db)
    {
        try{
            SQLiteDatabase DB = db.getReadableDatabase();
            String[] coloumns = {SeriesRepository.SeriesTableDetails.COLUMN_ID, SeriesTableDetails.COLUMN_POWTORZENIA, SeriesTableDetails.COLUMN_OBCIAZENIE,
                    SeriesTableDetails.COLUMN_ID_CW, SeriesTableDetails.COLUMN_ID_TR};

            Cursor CR = DB.query(SeriesRepository.SeriesTableDetails.TABLE_NAME, coloumns,null,null,null,null, InformationRepository.InformationTableDetails.COLUMN_ID+" DESC");

            CR.moveToFirst();
            Series userData= new Series(Integer.parseInt(CR.getString(0)),(Integer.parseInt(CR.getString(1))),
                    (Float.parseFloat(CR.getString(2))),(Integer.parseInt(CR.getString(3))),(Integer.parseInt(CR.getString(4))));

            Log.d("DataBase operations","Data recievied from DB");

//            do{
//                Log.d("Dane z bazy" , CR.getString(0)+" "+CR.getString((1)));
//            }while(CR.moveToNext());

            return userData;
        }
        catch (Exception ex){
            return  new Series();
        }

    }
}
