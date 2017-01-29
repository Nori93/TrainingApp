package com.example.norbert.myapplication.Engin.Repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.Helpers.TrainingHelper;
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
        public static final String COLUMN_NAZWA = "nazwa";
        public static final String COLUMN_OPIS = "opis";
    }


    public void SetDateToTraining(String date, int Id_tr,DatabaseOperations db)
    {
        Training currentTraining = this.GetTrainingById(Id_tr,db);
        currentTraining = TrainingHelper.TrainingDateHelper.AddToDate(currentTraining,date);

        this.EditTrainingData(currentTraining,db);


    }

    public void DeleteDateFromTraining(String date, int Id_tr,DatabaseOperations db)
    {
        Training currentTraining = this.GetTrainingById(Id_tr,db);
        currentTraining = TrainingHelper.TrainingDateHelper.DeleteFromDate(currentTraining,date);

        this.EditTrainingData(currentTraining,db);

    }


    public void EditTrainingStructure(Training training,DatabaseOperations db){


    }

        public void EditTrainingData(Training training,DatabaseOperations db){  //Use when you dont adding/deleteing series, ONLY FOR CHANGEING DATA
        SQLiteDatabase DB = db.getWritableDatabase();


        ContentValues TrainingTableContent = new ContentValues();
        ContentValues SeriesContentValue = new ContentValues();

        DB.beginTransaction();
        try{
            TrainingTableContent.put(TrainingTableDetails.COLUMN_DATA,training.getData());
            TrainingTableContent.put(TrainingTableDetails.COLUMN_NAZWA,training.getNazwa());
            TrainingTableContent.put(TrainingTableDetails.COLUMN_OPIS,training.getOpis());

            String whereClause = TrainingTableDetails._ID+"= ?";
            String[] whereArgs = new String[]{
                    Integer.toString(training.getID())
            };


            if(-1 == DB.update(TrainingTableDetails.TABLE_NAME,TrainingTableContent,whereClause,whereArgs))
            {
                throw new Exception();
            }

            for(Series seria : training.getSerie())
            {

                whereClause = SeriesRepository.SeriesTableDetails.COLUMN_ID+"= ?";
                whereArgs = new String[]{
                        Integer.toString(seria.getId())
                };

                SeriesContentValue.clear();
                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_POWTORZENIA, seria.getRepeats());
                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_OBCIAZENIE, seria.getWeights());
                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_ID_CW, seria.getId_cw());

                if(-1 == DB.update(SeriesRepository.SeriesTableDetails.TABLE_NAME,SeriesContentValue,whereClause,whereArgs))
                {
                    throw new Exception();
                }
            }

            Log.d("Training repository", "Row Edited");

            DB.setTransactionSuccessful();
        }catch (Exception ex)
        {
            Log.d("Training repository", "Exception while Editihng data to DB. Transaction aborted");
        }finally {
            DB.endTransaction();
        }


    }

    public ArrayList<Training> getAllTraining(DatabaseOperations db)
    {
        try
        {
            ArrayList<Training> Trainings = new ArrayList<>();
            SQLiteDatabase DB = db.getWritableDatabase();
            String[] coloumnsTraining = {TrainingTableDetails.COLUMN_ID, TrainingTableDetails.COLUMN_DATA,TrainingTableDetails.COLUMN_NAZWA,TrainingTableDetails.COLUMN_OPIS};
            Cursor TrainingCR = DB.query(TrainingTableDetails.TABLE_NAME, coloumnsTraining,null,null,null,null,null);
            TrainingCR.moveToFirst();

            String[] columnsSeries = {SeriesRepository.SeriesTableDetails.COLUMN_ID, SeriesRepository.SeriesTableDetails.COLUMN_POWTORZENIA, SeriesRepository.SeriesTableDetails.COLUMN_OBCIAZENIE,
                    SeriesRepository.SeriesTableDetails.COLUMN_ID_CW, SeriesRepository.SeriesTableDetails.COLUMN_ID_TR};
            String whereClause = SeriesRepository.SeriesTableDetails.COLUMN_ID_TR+"= ?";

            do{
                List<Series> serie = new ArrayList<>();
                String[] whereArgs = new String[] {
                        TrainingCR.getString(0)
                };

                Cursor SeriesCR = DB.query(SeriesRepository.SeriesTableDetails.TABLE_NAME, columnsSeries,whereClause,whereArgs,null,null,null);
                SeriesCR.moveToFirst();

                do{
                    if(SeriesCR.getCount() > 0)
                    serie.add(new Series(Integer.parseInt(SeriesCR.getString(0)),Integer.parseInt(SeriesCR.getString(1)),Float.parseFloat(SeriesCR.getString(2)),Integer.parseInt(SeriesCR.getString(3)),Integer.parseInt(SeriesCR.getString(4))));
                }while(SeriesCR.moveToNext());

                Trainings.add(new Training(Integer.parseInt(TrainingCR.getString(0)),TrainingCR.getString(1),serie,TrainingCR.getString(2),TrainingCR.getString(3)));
            }while(TrainingCR.moveToNext());

            return  Trainings;
        }
        catch (Exception ex)
        {
            return  new ArrayList<>();
        }

    }

    public void insertNewTrening(Training training,DatabaseOperations db)//Training -> ID Series all ID's MUST BE NULL!!!!
    {
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues TrainingTableContent = new ContentValues();
        ContentValues SeriesContentValue = new ContentValues();


        try{
            TrainingTableContent.put(TrainingTableDetails.COLUMN_DATA,training.getData());
            TrainingTableContent.put(TrainingTableDetails.COLUMN_NAZWA,training.getNazwa());
            TrainingTableContent.put(TrainingTableDetails.COLUMN_OPIS,training.getOpis());


            if(-1 == DB.insert(TrainingTableDetails.TABLE_NAME,null,TrainingTableContent))
            {
                throw new Exception();
            }

            String[] coloumnsTraining = {TrainingTableDetails.COLUMN_ID, TrainingTableDetails.COLUMN_DATA,TrainingTableDetails.COLUMN_NAZWA,TrainingTableDetails.COLUMN_OPIS};

            Cursor CR = DB.query(TrainingTableDetails.TABLE_NAME, coloumnsTraining,null,null,null,null, TrainingTableDetails.COLUMN_ID+" DESC");
            CR.moveToFirst();
            String TrainingID = "-1";

            Log.d("d",CR.getString(2));

        //    if(training.getNazwa() == CR.getString(2))
         //   {
                TrainingID = CR.getString(0);
        //    }

            for(Series seria : training.getSerie())
            {
                SeriesContentValue.clear();

                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_POWTORZENIA, seria.getRepeats());
                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_OBCIAZENIE, seria.getWeights());
                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_ID_CW, seria.getId_cw());
                SeriesContentValue.put(SeriesRepository.SeriesTableDetails.COLUMN_ID_TR, TrainingID);

                if(-1 == DB.insert(SeriesRepository.SeriesTableDetails.TABLE_NAME,null,SeriesContentValue))
                {
                    throw new Exception();
                }
            }

            Log.d("DataBase operations", "Row inserted to series");

        }catch (Exception ex)
        {
            Log.d("Training repository", "Exception while inserting data to DB. Transaction aborted");
        }
    }

    public boolean DeleteExistingTraining(int trainingId,DatabaseOperations db){ //return false if exception was throwed

        SQLiteDatabase DB = db.getWritableDatabase();
        try{
            DB.beginTransaction();
            String whereClause = TrainingTableDetails.COLUMN_ID+"= ?";
            String[] whereArgs = new String[] {
                    Integer.toString(trainingId)
            };
            DB.delete(TrainingTableDetails.TABLE_NAME,whereClause,whereArgs);

            whereClause = SeriesRepository.SeriesTableDetails.COLUMN_ID_TR+"= ?";
            DB.delete(SeriesRepository.SeriesTableDetails.TABLE_NAME,whereClause,whereArgs);
            DB.setTransactionSuccessful();
            Log.d("DataBase operations", "Training successfully deleted");
            return  true;
        }
        catch (Exception ex)
        {
            return false;
        }
        finally {
            DB.endTransaction();
        }
    }
    public  List<Training> GetTrainingByDate(String trainingDate,DatabaseOperations db)
    {
        try
        {
            List<Training> Trainings = new ArrayList<>();
            SQLiteDatabase DB = db.getWritableDatabase();
            String[] coloumnsTraining = {TrainingTableDetails.COLUMN_ID, TrainingTableDetails.COLUMN_DATA,TrainingTableDetails.COLUMN_NAZWA,TrainingTableDetails.COLUMN_OPIS};
            String whereClause = TrainingTableDetails.COLUMN_DATA+" LIKE ?";
            String[] whereArgs = new String[] {
                    "%"+trainingDate+"%"
            };
            Cursor TrainingCR = DB.query(TrainingTableDetails.TABLE_NAME, coloumnsTraining,whereClause,whereArgs,null,null,null);
            TrainingCR.moveToFirst();

            String[] columnsSeries = {SeriesRepository.SeriesTableDetails.COLUMN_ID, SeriesRepository.SeriesTableDetails.COLUMN_POWTORZENIA, SeriesRepository.SeriesTableDetails.COLUMN_OBCIAZENIE,
                    SeriesRepository.SeriesTableDetails.COLUMN_ID_CW, SeriesRepository.SeriesTableDetails.COLUMN_ID_TR};
             whereClause = SeriesRepository.SeriesTableDetails.COLUMN_ID_TR+"= ?";

            do{
                List<Series> serie = new ArrayList<>();
                    whereArgs = new String[] {
                        TrainingCR.getString(0)
                };

                Cursor SeriesCR = DB.query(SeriesRepository.SeriesTableDetails.TABLE_NAME, columnsSeries,whereClause,whereArgs,null,null,null);
                SeriesCR.moveToFirst();

                do{
                    if(SeriesCR.getCount() > 0)
                        serie.add(new Series(Integer.parseInt(SeriesCR.getString(0)),Integer.parseInt(SeriesCR.getString(1)),Float.parseFloat(SeriesCR.getString(2)),Integer.parseInt(SeriesCR.getString(3)),Integer.parseInt(SeriesCR.getString(4))));
                }while(SeriesCR.moveToNext());

                Trainings.add(new Training(Integer.parseInt(TrainingCR.getString(0)),TrainingCR.getString(1),serie,TrainingCR.getString(2),TrainingCR.getString(3)));
            }while(TrainingCR.moveToNext());

            return  Trainings;
        }
        catch (Exception ex)
        {
            return  new ArrayList<>();
        }
    }

    public  Training GetTrainingById(int trainingId,DatabaseOperations db)
    {
        try
        {
            List<Series> serie = new ArrayList<>();
            SQLiteDatabase DB = db.getReadableDatabase();
            String[] coloumnsTraining = {TrainingTableDetails.COLUMN_ID, TrainingTableDetails.COLUMN_DATA,TrainingTableDetails.COLUMN_NAZWA,TrainingTableDetails.COLUMN_OPIS};

            String whereClause = TrainingTableDetails.COLUMN_ID+"= ?";
            String[] whereArgs = new String[] {
                    Integer.toString(trainingId)
            };

            Cursor TrainingCR = DB.query(TrainingTableDetails.TABLE_NAME, coloumnsTraining,whereClause,whereArgs,null,null,null);
            TrainingCR.moveToFirst();

            Log.d("Traiing", TrainingCR.getString(1));

            String[] columnsSeries = {SeriesRepository.SeriesTableDetails.COLUMN_ID, SeriesRepository.SeriesTableDetails.COLUMN_POWTORZENIA, SeriesRepository.SeriesTableDetails.COLUMN_OBCIAZENIE,
                    SeriesRepository.SeriesTableDetails.COLUMN_ID_CW, SeriesRepository.SeriesTableDetails.COLUMN_ID_TR};

            whereClause = SeriesRepository.SeriesTableDetails.COLUMN_ID_TR+"= ?";

            Cursor SeriesCR = DB.query(SeriesRepository.SeriesTableDetails.TABLE_NAME, columnsSeries,whereClause,whereArgs,null,null,null);
            SeriesCR.moveToFirst();

            do{
                serie.add(new Series(Integer.parseInt(SeriesCR.getString(0)),Integer.parseInt(SeriesCR.getString(1)),Float.parseFloat(SeriesCR.getString(2)),Integer.parseInt(SeriesCR.getString(3)),Integer.parseInt(SeriesCR.getString(4))));
            }while(SeriesCR.moveToNext());

            return new Training(Integer.parseInt(TrainingCR.getString(0)),TrainingCR.getString(1),serie,TrainingCR.getString(2),TrainingCR.getString(3));
        }
        catch (Exception ex){
            List<Series> serie = new ArrayList<>();
            return  new Training(-1,"01-01-1970",serie,"brak","brak");
        }
    }
}
