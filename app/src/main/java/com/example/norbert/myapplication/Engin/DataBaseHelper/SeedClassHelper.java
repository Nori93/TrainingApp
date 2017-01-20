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
            StatementList.add("insert into cwiczenia  values (NULL,'WYCISKANIE SZTANGI W LEŻENIU NA ŁAWCE POZIOMEJ','Kładziemy się na ławce tak, by nogi ugięte były pod kątem prostym i przylegały do podłoża. Uchwyt średni(taki, by po opuszczeniu sztangi na klatkę ramiona tworzyły z przedramionami kąt prosty-kciuk dla bezpieczeństwa powinien obejmować sztangę-choć wielu bardziej doświadczonych kulturystów preferuje raczej tzw. ”małpi chwyt” (kciuk ponad gryfem).Opuszczamy sztangę na klatkę na wysokość ok.1 cm powyżej brodawek. Przy opuszczaniu sztangi wykonujemy głęboki wdech-wydychamy powietrze w trakcie wyciskania. Można okresowo praktykować przytrzymywanie sztangi przez chwilę na klatce przed wyciśnięciem.(szczególnie przydatne, jeżeli mamy w planach ewentualne starty w zawodach w wyciskaniu)-dodatkowo rozbudowuje siłę-pobudza do dodatkowego wysiłku. Łokcie prowadzimy w trakcie całego ruchu po bokach-tak by nie „uciekały”do środka. Ruch wyciskania kończymy(dla lepszego napięcia mięśni)zanim łokcie zostaną zblokowane. Ćwiczenie to można wykonywać również na maszynach');");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");
            StatementList.add("insert into cwiczenia values ('','')");

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
