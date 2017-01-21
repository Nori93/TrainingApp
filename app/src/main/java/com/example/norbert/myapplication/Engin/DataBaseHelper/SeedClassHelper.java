package com.example.norbert.myapplication.Engin.DataBaseHelper;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.security.cert.Extension;
import java.util.ArrayList;

import static java.lang.System.in;

/**
 * Created by Czajmen on 20.01.2017.
 */

public class SeedClassHelper {
    public  static class DBSeeder{

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
            }
            catch (Exception ex){
                Log.d("Database Seeder", "Error");
            }
        }
    }
}
