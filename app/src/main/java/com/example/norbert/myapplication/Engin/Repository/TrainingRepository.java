package com.example.norbert.myapplication.Engin.Repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.Objects.Series;
import com.example.norbert.myapplication.Engin.Objects.Training;

import java.util.ArrayList;
import java.util.List;

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


            if(-1 == DB.insert(TrainingTableDetails.TABLE_NAME,null,TrainingTableContent))
            {
                throw new Exception();
            }

            for(Series seria : training.getSerie())
            {
                SeriesContentValue.clear();

                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_POWTORZENIA, seria.getRepeats());
                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_OBCIAZENIE, seria.getWeights());
                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_ID_CW, seria.getId_cw());
                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_ID_TR, seria.getId_tr());

                if(-1 == DB.insert(SeriesRepository.SeriesTableDetails.TABLE_NAME,null,SeriesContentValue))
                {
                    throw new Exception();
                }
            }

            Log.d("DataBase operations", "Row inserted to series");

            DB.setTransactionSuccessful();
        }catch (Exception ex)
        {
            Log.d("Training repository", "Exception while inserting data to DB. Transaction aborted");
        }finally {
            DB.endTransaction();
        }
    }

    public void DeleteExistingTraining(int trainingId,DatabaseOperations db){

    }

    public  Training GetTrainingById(int trainingId,DatabaseOperations db)
    {
        List<Series> serie = new ArrayList<>();
        SQLiteDatabase DB = db.getReadableDatabase();
        String[] coloumnsTraining = {TrainingTableDetails.COLUMN_ID, TrainingTableDetails.COLUMN_DATA};

        String whereClause = TrainingTableDetails.COLUMN_ID+"= ?";
        String[] whereArgs = new String[] {
                Integer.toString(trainingId)
        };

        Cursor TrainingCR = DB.query(TrainingTableDetails.TABLE_NAME, coloumnsTraining,whereClause,whereArgs,null,null,null);
        TrainingCR.moveToFirst();

        String[] columnsSeries = {SeriesRepository.SeriesTableDetails.COLUMN_ID, SeriesRepository.SeriesTableDetails.COLUMN_POWTORZENIA, SeriesRepository.SeriesTableDetails.COLUMN_OBCIAZENIE,
                SeriesRepository.SeriesTableDetails.COLUMN_ID_CW, SeriesRepository.SeriesTableDetails.COLUMN_ID_TR};

         whereClause = SeriesRepository.SeriesTableDetails.COLUMN_ID_TR+"= ?";

        Cursor SeriesCR = DB.query(SeriesRepository.SeriesTableDetails.TABLE_NAME, columnsSeries,whereClause,whereArgs,null,null,null);

        do{
            serie.add(new Series(Integer.parseInt(SeriesCR.getString(0)),Integer.parseInt(SeriesCR.getString(1)),Float.parseFloat(SeriesCR.getString(2)),Integer.parseInt(SeriesCR.getString(3)),Integer.parseInt(SeriesCR.getString(4))));
          }while(SeriesCR.moveToNext());

        return new Training(Integer.parseInt(TrainingCR.getString(0)),TrainingCR.getString(1),serie);
    }
}
