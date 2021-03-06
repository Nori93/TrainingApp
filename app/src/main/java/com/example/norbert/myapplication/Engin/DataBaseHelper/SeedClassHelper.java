package com.example.norbert.myapplication.Engin.DataBaseHelper;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.security.cert.Extension;
import java.util.ArrayList;

import static java.lang.System.in;

/**
 * Created by Czajmen on 20.01.2017.
 */

public class SeedClassHelper {
    public  static class DBSeeder{

        public static final String DATABASE_NAME = "cwiczenaia";
        public static final String DATABASE_PATH = "path";

        public static ArrayList<String> PrepareStatement(){

            ArrayList<String> StatementList = new ArrayList<String>();
            StatementList.add("insert into cwiczenia values (NULL,'nazwa','opis','instrukcje','sciezka')");
            StatementList.add("insert into cwiczenia values (NULL,'nazwa2','opis','instrukcje','sciezka')");
            StatementList.add("insert into cwiczenia values (NULL,'nazwa3','opis','instrukcje','sciezka')");
            StatementList.add("insert into cwiczenia values (NULL,'nazwa4','opis','instrukcje','sciezka')");

            return StatementList;
        }


        public static void SeedDataBase(SQLiteDatabase db){

            try{
                ArrayList<String> StatementList = SeedClassHelper.DBSeeder.PrepareStatement();

                for(String query : StatementList)
                {
                    db.execSQL(query);
                }

                try {
                    SQLiteDatabase checkDB = SQLiteDatabase.openDatabase(DATABASE_PATH, null,
                            SQLiteDatabase.OPEN_READONLY);

                    db.execSQL("INSERT INTO cwiczenia SELECT * FROM ");

                }
                catch (SQLiteException ex) {
                    Log.d("db log", "database does't exist");
                }

            }
            catch (Exception ex){
                Log.d("Database Seeder", "Error");
            }
        }
    }
}
