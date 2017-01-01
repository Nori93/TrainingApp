package com.example.norbert.myapplication.Gui;



import com.example.norbert.myapplication.Gui.Fragments.ExerciseWindow;
import com.example.norbert.myapplication.Gui.Fragments.ListWindow;
import com.example.norbert.myapplication.Gui.Fragments.StartWindow;
import com.example.norbert.myapplication.Gui.Fragments.TopControlPanel;

public class FragmertsHolder {

    //Hold which fragment we use right now
    private int index;

    //List of fragments for app
    StartWindow startFragment;
    TopControlPanel topControlPanel;
    ListWindow listFragment;
    ExerciseWindow exerciseWindow;

    public FragmertsHolder(int i){
        this.index = i;
        switch (index){
            case 0:
                startFragment = new StartWindow();
                topControlPanel = new TopControlPanel();
                break;
            case 1:
                listFragment = new ListWindow();
                break;
            case 2:
                exerciseWindow = new ExerciseWindow();
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }


    // Get The Fragment and give to MainWindow
    public StartWindow getStartFragment(){return startFragment;}
    public TopControlPanel getTopControlPanel(){return topControlPanel;}
    public ExerciseWindow getExerciseWindow(){return exerciseWindow;}
    public ListWindow getListFragment(){return listFragment;}


    //Clear Frame to clear space
    public void destroyStartFragment(){this.startFragment = null;}
    public void destroyTopControlPanel(){this.topControlPanel = null;}
    public void destroyExerciseWindow(){this.exerciseWindow = null;}
    public void destroyListFragment(){this.listFragment = null;}
}
