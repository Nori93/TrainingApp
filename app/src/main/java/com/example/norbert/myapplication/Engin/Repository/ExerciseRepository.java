package com.example.norbert.myapplication.Engin.Repository;

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
        public static final String COLUMN_ID = "ID";
    }

    public Exercise getExerciseById(int Id){

        return  new Exercise(1,"nazwa","opis");
    }

    public ArrayList<Exercise> getAllExercise(DatabaseOperations db){

        try{
            ArrayList<Exercise> ExerciseArrayList = new ArrayList<Exercise>();
            SQLiteDatabase DB = db.getReadableDatabase();
            String[] coloumns = {ExerciseTableDetails.COLUMN_ID,ExerciseTableDetails.COLUMN_NAZWA,ExerciseTableDetails.COLUMN_OPIS};

            Cursor CR = DB.query(ExerciseTableDetails.TABLE_NAME, coloumns,null,null,null,null,null);
            CR.moveToFirst();

            do{
              ExerciseArrayList.add(new Exercise(Integer.parseInt(CR.getString(0)),CR.getString(1),CR.getString(2)));
            }while(CR.moveToNext());

            Log.d("DataBase operations","Recieved all exercises");

            return ExerciseArrayList ;
        }
        catch (Exception ex){
            return  new ArrayList<Exercise>();
        }
        }
}
