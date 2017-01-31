package com.example.norbert.myapplication.Engin.Repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Objects.UserInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil Czaja on 20.01.2017.
 *  All rights reserved
 */

public class ExerciseRepository {

    public static abstract class ExerciseTableDetails implements BaseColumns {

        public static final String TABLE_NAME = "cwiczenia";
        public static final String COLUMN_NAZWA= "nazwa";
        public static final String COLUMN_OPIS = "opis";
        public static final String COLUMN_INSTRUKCJE = "instrukcje";
        public static final String COLUMN_SCIEZKA = "path";
        public static final String COLUMN_ID = "ID";
    }


    public void insertNewExcerise(Exercise exercise,DatabaseOperations db){
        try{


            SQLiteDatabase DB = db.getWritableDatabase();
            ContentValues content = new ContentValues();
            content.put(ExerciseTableDetails.COLUMN_NAZWA, exercise.getNazwa());
            content.put(ExerciseTableDetails.COLUMN_OPIS, exercise.getOpis());
            content.put(ExerciseTableDetails.COLUMN_INSTRUKCJE, exercise.getInstrukcje());
            content.put(ExerciseTableDetails.COLUMN_SCIEZKA, exercise.getSciezka());


            long success = DB.insert(ExerciseTableDetails.TABLE_NAME,null,content);
            Log.d("DataBase operations","inserted row exercise");

        }
        catch (Exception ex){
            Log.d("DataBase operations","error while inserting row exercise");
        }
    }

    public void deleteExerciseByID(int Id, DatabaseOperations db){

        SQLiteDatabase DB = db.getWritableDatabase();
        try{
            String whereClause = ExerciseTableDetails.COLUMN_ID+"= ?";
            String[] whereArgs = new String[] {
                    Integer.toString(Id)
            };
            DB.delete(ExerciseTableDetails.TABLE_NAME,whereClause,whereArgs);

            Log.d("DataBase operations", "Training successfully deleted");
        }
        catch (Exception ex)
        {

        }
    }

    public Exercise getExerciseById(int Id,DatabaseOperations db){


        try{
            ArrayList<Exercise> ExerciseArrayList = new ArrayList<Exercise>();
            SQLiteDatabase DB = db.getReadableDatabase();
            String[] coloumns = {ExerciseTableDetails.COLUMN_ID,ExerciseTableDetails.COLUMN_NAZWA,ExerciseTableDetails.COLUMN_OPIS,ExerciseTableDetails.COLUMN_INSTRUKCJE,ExerciseTableDetails.COLUMN_SCIEZKA};

            String whereClause = ExerciseTableDetails.COLUMN_ID+"= ?";
            String[] whereArgs = new String[] {
                   Integer.toString(Id)
            };

            Cursor CR = DB.query(ExerciseTableDetails.TABLE_NAME, coloumns,whereClause,whereArgs,null,null,null);
            CR.moveToFirst();

            Log.d("DataBase operations","Recieved exercise");
            return new Exercise(Integer.parseInt(CR.getString(0)),CR.getString(1),CR.getString(2),CR.getString(3),CR.getString(4));
        }
        catch (Exception ex){
            return  new Exercise(-1,"Nie ma takiego cwiczenia","Nie ma takiego cwiczenia","","");
        }

    }

    public ArrayList<Exercise> getAllExercise(DatabaseOperations db)
        {
            try{
                ArrayList<Exercise> ExerciseArrayList = new ArrayList<Exercise>();
                SQLiteDatabase DB = db.getReadableDatabase();
                String[] coloumns = {ExerciseTableDetails.COLUMN_ID,ExerciseTableDetails.COLUMN_NAZWA,ExerciseTableDetails.COLUMN_OPIS,ExerciseTableDetails.COLUMN_INSTRUKCJE,ExerciseTableDetails.COLUMN_SCIEZKA};

                Cursor CR = DB.query(ExerciseTableDetails.TABLE_NAME, coloumns,null,null,null,null,null);
                CR.moveToFirst();

                do{
                  ExerciseArrayList.add(new Exercise(Integer.parseInt(CR.getString(0)),CR.getString(1),CR.getString(2),CR.getString(3),CR.getString(4)));
                }while(CR.moveToNext());

                Log.d("DataBase operations","Recieved all exercises");

                return ExerciseArrayList ;
            }
            catch (Exception ex){
                return  new ArrayList<Exercise>();
            }
        }
}
