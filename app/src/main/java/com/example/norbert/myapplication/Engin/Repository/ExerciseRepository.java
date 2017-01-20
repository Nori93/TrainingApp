package com.example.norbert.myapplication.Engin.Repository;

import android.provider.BaseColumns;

/**
 * Created by Czajmen on 20.01.2017.
 *  All rights reserved
 */

public class ExerciseRepository {

    public static abstract class ExerciseTableDetails implements BaseColumns {

        public static final String TABLE_NAME = "cwiczenia";
        public static final String COLUMN_NAZWA= "nazwa";
        public static final String COLUMN_OPIS = "opis";
        public static final String COLUMN_ID = "ID";
    }

}
