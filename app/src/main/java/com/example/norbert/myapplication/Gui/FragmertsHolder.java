package com.example.norbert.myapplication.Gui;



import android.app.Fragment;

import com.example.norbert.myapplication.Gui.Fragments.AddTrainingWindow;
import com.example.norbert.myapplication.Gui.Fragments.CalendarWindow;
import com.example.norbert.myapplication.Gui.Fragments.ExerciseWindow;
import com.example.norbert.myapplication.Gui.Fragments.ListWindow;
import com.example.norbert.myapplication.Gui.Fragments.ProfilWindow;
import com.example.norbert.myapplication.Gui.Fragments.StartWindow;
import com.example.norbert.myapplication.Gui.Fragments.TopControlPanel;
import com.example.norbert.myapplication.Gui.Fragments.TrainingListWindow;

public class FragmertsHolder {

    //Hold which fragment we use right now
    private int index;

    //List of fragments for app
    StartWindow startFragment;              // 1
    TopControlPanel topControlPanel;        // 2
    ListWindow listFragment_Workout;        // 3
    ListWindow listFragment_Meals;          // 4
    ExerciseWindow exerciseWindow;          // 5
    ProfilWindow profilWindow;              // 6
    CalendarWindow calendarWindow;          // 7
    TrainingListWindow trainingListWindow;  // 8
    AddTrainingWindow addTrainingWindow;    // 9
    public FragmertsHolder(){

        startFragment = new StartWindow();
        topControlPanel = new TopControlPanel();
        listFragment_Workout = new ListWindow();
        listFragment_Meals=new ListWindow();
        profilWindow = new ProfilWindow();
        exerciseWindow = new ExerciseWindow();
        calendarWindow = new CalendarWindow();
        trainingListWindow=new TrainingListWindow();
        trainingListWindow=new TrainingListWindow();
        addTrainingWindow= new AddTrainingWindow();
    }


    // version 0.1
    /**
    public StartWindow getStartFragment(){return startFragment;}
    public TopControlPanel getTopControlPanel(){return topControlPanel;}
    public ExerciseWindow getExerciseWindow(){return exerciseWindow;}
    public ListWindow getListFragment_Workout(){return listFragment_Workout;}
    public ListWindow getListFragment_Meals(){return listFragment_Meals;}
    public ProfilWindow getProfilWindow(){return profilWindow;}
    public CalendarWindow getCalendarWindow(){return calendarWindow;}
    public TrainingListWindow getTrainingListWindow(){return  trainingListWindow;}
    public AddTrainingWindow getAddTrainingWindow(){return addTrainingWindow;};
    */
    // version 0.2
    public Fragment getFragment(int index) {
        switch (index) {
            case 1:
                return startFragment;
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

        }
        return null;
    }



    //Clear Frame to clear space
    public void destroyStartFragment(){this.startFragment = null;}
    public void destroyTopControlPanel(){this.topControlPanel = null;}
    public void destroyExerciseWindow(){this.exerciseWindow = null;}
    public void destroyListFragment_Workout(){this.listFragment_Workout = null;}
    public void destroyListFragment_Meals(){this.listFragment_Meals = null;}
    public void destroyProfilWindow(){this.profilWindow = null;}
    public void destroyTrainingListWindow(){this.trainingListWindow=null;}
    public void destroyAddTrainingWindow(){this.addTrainingWindow=null;}


}
