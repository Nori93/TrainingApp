package com.example.norbert.myapplication.Gui;



import android.app.Fragment;
import android.content.Context;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.ObjectSchema;
import com.example.norbert.myapplication.Gui.Fragments.AddTrainingWindow;
import com.example.norbert.myapplication.Gui.Fragments.CalendarWindow;
import com.example.norbert.myapplication.Gui.Fragments.ExerciseWindow;
import com.example.norbert.myapplication.Gui.Fragments.InputWindow;
import com.example.norbert.myapplication.Gui.Fragments.ListWindow;
import com.example.norbert.myapplication.Gui.Fragments.ProfilWindow;
import com.example.norbert.myapplication.Gui.Fragments.StartWindow;
import com.example.norbert.myapplication.Gui.Fragments.TopControlPanel;
import com.example.norbert.myapplication.Gui.Fragments.TrainingListWindow;
import com.example.norbert.myapplication.R;

public class FragmertsHolder {

    //Hold which fragment we use right now
    private Context context;

    //List of fragments for app
    StartWindow startWindow;                // 1
    TopControlPanel topControlPanel;        // 2
    ListWindow listFragment_Workout;        // 3
    ListWindow listFragment_Meals;          // 4
    ExerciseWindow exerciseWindow;          // 5
    ProfilWindow profilWindow;              // 6
    CalendarWindow calendarWindow;          // 7
    TrainingListWindow trainingListWindow;  // 8
    AddTrainingWindow addTrainingWindow;    // 9
    ListWindow listFragment_TrainingCreating;//11
    InputWindow inputWindow;
    public FragmertsHolder(Context context){
        this.context = context;

        startWindow = new StartWindow();
        topControlPanel = new TopControlPanel();
        listFragment_Workout = new ListWindow();
        listFragment_Meals=new ListWindow();
        listFragment_TrainingCreating=new ListWindow();
        profilWindow = new ProfilWindow();
        exerciseWindow = new ExerciseWindow();
        calendarWindow = new CalendarWindow();
        trainingListWindow=new TrainingListWindow();
        trainingListWindow=new TrainingListWindow();
        addTrainingWindow= new AddTrainingWindow();
        inputWindow = new InputWindow();
    }

    public Fragment getFragment(int index) {
        switch (index) {
            case 1:
                return startWindow;
            case 2:
                return topControlPanel;
            case 3:
                return listFragment_Workout;
            case 4:
                return listFragment_Meals;
            case 5:
                return exerciseWindow;
            case 6:
                return profilWindow;
            case 7:
                return calendarWindow;
            case 8:
                return trainingListWindow;
            case 9:
                return addTrainingWindow;
            case 10:
                return inputWindow;
            case 11:
                return listFragment_TrainingCreating;
        }
        return null;
    }

    public void setInputFragment(int index) {

                inputWindow.setInputTag(getTag(index));

    }


    //Clear Frame to clear space
    public void destroyStartFragment(){this.startWindow = null;}
    public void destroyTopControlPanel(){this.topControlPanel = null;}
    public void destroyExerciseWindow(){this.exerciseWindow = null;}
    public void destroyListFragment_Workout(){this.listFragment_Workout = null;}
    public void destroyListFragment_Meals(){this.listFragment_Meals = null;}
    public void destroyProfilWindow(){this.profilWindow = null;}
    public void destroyTrainingListWindow(){this.trainingListWindow=null;}
    public void destroyAddTrainingWindow(){this.addTrainingWindow=null;}

    private int getTag(int resorce) {
        return context.getResources().getInteger(resorce);
    }

}
