package com.example.norbert.myapplication.Engin.Repository;

import android.provider.BaseColumns;

/**
 * Created by Czajmen on 20.01.2017.
 */

public class TrainingRepository {
    public static abstract class TrainingTableDetails implements BaseColumns {

        public static final String TABLE_NAME = "trening";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_DATA = "data";
    }

}
