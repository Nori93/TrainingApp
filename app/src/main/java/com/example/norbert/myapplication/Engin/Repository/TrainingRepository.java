package com.example.norbert.myapplication.Engin.Repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.Objects.Training;

/**
 * Created by Kamil Czaja on 20.01.2017.
 */

public class TrainingRepository {
    public static abstract class TrainingTableDetails implements BaseColumns {

        public static final String TABLE_NAME = "trening";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_DATA = "data";
    }

    public void AddNewTrening(Training training,DatabaseOperations db)//Training -> ID Series all ID's MUST BE NULL!!!!
    {
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues TrainingTableContent = new ContentValues();
        ContentValues SeriesContentValue = new ContentValues();

        DB.beginTransaction();
        try{
            TrainingTableContent.put(TrainingTableDetails.COLUMN_DATA,training.getData());

            if(-1 == DB.insert(TrainingTableDetails.TABLE_NAME,null,TrainingTableContent)
               || -1 ==  DB.insert(SeriesRepository.SeriesTableDetails.TABLE_NAME,null,SeriesContentValue)){
                throw new Exception();
            }
            DB.setTransactionSuccessful();
        }catch (Exception ex)
        {
            Log.d("Training repository", "Exception while inserting data to DB. Transaction aborted");
        }finally {
            DB.endTransaction();
        }
    }

}
