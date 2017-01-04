package com.example.norbert.myapplication.Engin.DataBaseHelper;

/**
 * Created by Kamil on 2017-01-03.
 */

import android.provider.BaseColumns;


public class InformationRepository{

    public InformationRepository(){}

    public static abstract class InformationInfo implements BaseColumns {

        public static final String DATABASE_NAME = "cwiczenia";
        public static final String TABLE_NAME = "Informacje";

        public static final String COLUMN_WAGA = "waga";
        public static final String COLUMN_WZROST = "wzrost";
        public static final String COLUMN_KALORIE = "ilosc_kalorii";
        public static final String COLUMN_POZIOM = "poziom_aktywnosci";
        public static final String COLUMN_TLUSZCZ = "tluszcz";
        public static final String COLUMN_WEGLOWODANY = "weglowodany";
        public static final String COLUMN_BIALKO = "bialko";
    }


}

