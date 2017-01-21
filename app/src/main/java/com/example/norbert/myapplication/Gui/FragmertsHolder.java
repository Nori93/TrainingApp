package com.example.norbert.myapplication.Gui;



import com.example.norbert.myapplication.Gui.Fragments.CalendarWindow;
import com.example.norbert.myapplication.Gui.Fragments.ExerciseWindow;
import com.example.norbert.myapplication.Gui.Fragments.ListWindow;
import com.example.norbert.myapplication.Gui.Fragments.ProfilWindow;
import com.example.norbert.myapplication.Gui.Fragments.StartWindow;
import com.example.norbert.myapplication.Gui.Fragments.TopControlPanel;

public class FragmertsHolder {

    //Hold which fragment we use right now
    private int index;

    //List of fragments for app
    StartWindow startFragment;
    TopControlPanel topControlPanel;
    ListWindow listFragment_Workout;
    ListWindow listFragment_Meals;
    ExerciseWindow exerciseWindow;
    ProfilWindow profilWindow;
    CalendarWindow calendarWindow;
    public FragmertsHolder(){

        startFragment = new StartWindow();
        topControlPanel = new TopControlPanel();

        listFragment_Workout = new ListWindow();
        listFragment_Meals=new ListWindow();
        profilWindow = new ProfilWindow();

        calendarWindow = new CalendarWindow();

    }


    // Get The Fragment and give to MainWindow
    public StartWindow getStartFragment(){return startFragment;}
    public TopControlPanel getTopControlPanel(){return topControlPanel;}
    public ExerciseWindow getExerciseWindow(){return exerciseWindow;}
    public ListWindow getListFragment_Workout(){return listFragment_Workout;}
    public ListWindow getListFragment_Meals(){return listFragment_Meals;}
    public ProfilWindow getProfilWindow(){return profilWindow;}
    public CalendarWindow getCalendarWindow(){return calendarWindow;}


    //Clear Frame to clear space
    public void destroyStartFragment(){this.startFragment = null;}
    public void destroyTopControlPanel(){this.topControlPanel = null;}
    public void destroyExerciseWindow(){this.exerciseWindow = null;}
    public void destroyListFragment_Workout(){this.listFragment_Workout = null;}
    public void destroyListFragment_Meals(){this.listFragment_Meals = null;}
    public void destroyProfilWindow(){this.profilWindow = null;}
}
