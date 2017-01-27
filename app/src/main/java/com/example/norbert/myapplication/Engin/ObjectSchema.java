package com.example.norbert.myapplication.Engin;

/**
 * Created by norbert on 25.01.2017.
 */

public class ObjectSchema {

    String[] exercise = {"ID","Name","Description","Instruction","Icon Path"};
    String[] series = {"ID","Repeats", "Weights", "ID Exercise", "ID Training"};
    String[] training = {"ID","Name","Description","Data"};
    String[] userInformation = {"Weight","Height", "Calories", "Activity","Fat", "Carb", "Protein", "Target","Body Type", "Sex","Age"};

    public String[] get(int tag){{
            switch (tag) {
                case 51:
                    return exercise;
                case 52:
                    return series;
                case 53:
                    return training;
                case 54:
                    return userInformation;

            }
            return null;
        }
    }
}
