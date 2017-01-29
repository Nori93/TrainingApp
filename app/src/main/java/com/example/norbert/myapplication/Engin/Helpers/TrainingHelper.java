package com.example.norbert.myapplication.Engin.Helpers;

import com.example.norbert.myapplication.Engin.Objects.Training;

/**
 * Created by Czajmen on 29.01.2017.
 */

public class TrainingHelper {

    public  static class  TrainingDateHelper{

        public static Training AddToDate(Training training,String date){

           String currentDate =  training.getData();
           String newDate = currentDate+";"+date;
            training.setData(newDate);

            return  training;
        }

        public static Training DeleteFromDate(Training training,String date){

            String currentDate =  training.getData();
            String newDate = removeWords(currentDate,date);
            training.setData(newDate);

            return  training;
        }


        public static String removeWords(String word ,String remove) {
            return word.replace(remove,"");
        }

    }


}
