package com.example.norbert.myapplication.Gui;



import android.app.Fragment;
import android.content.Context;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.ObjectSchema;
import com.example.norbert.myapplication.Engin.Objects.Series;
import com.example.norbert.myapplication.Gui.Fragments.AddTrainingWindow;
import com.example.norbert.myapplication.Gui.Fragments.CalendarWindow;
import com.example.norbert.myapplication.Gui.Fragments.ChooseExerciseDetails;
import com.example.norbert.myapplication.Gui.Fragments.ChooseExerciseWindow;
import com.example.norbert.myapplication.Gui.Fragments.ExerciseWindow;
import com.example.norbert.myapplication.Gui.Fragments.InputTrainingWindow;
import com.example.norbert.myapplication.Gui.Fragments.InputWindow;
import com.example.norbert.myapplication.Gui.Fragments.ListSelectWindow;
import com.example.norbert.myapplication.Gui.Fragments.ListWindow;
import com.example.norbert.myapplication.Gui.Fragments.ProfilWindow;
import com.example.norbert.myapplication.Gui.Fragments.StartWindow;
import com.example.norbert.myapplication.Gui.Fragments.TopControlPanel;
import com.example.norbert.myapplication.Gui.Fragments.TrainingDetails;
import com.example.norbert.myapplication.Gui.Fragments.TrainingListWindow;
import com.example.norbert.myapplication.R;

public class FragmertsHolder {

    //Hold which fragment we use right now
    private ObjectSchema os;
    private Context context;

    //List of fragments for app
    StartWindow startWindow;                // 1
    TopControlPanel topControlPanel;        // 2
    ListWindow listFragment_Workout;        // 3
 //   ListWindow listFragment_Meals;          // 4
    ExerciseWindow exerciseWindow;          // 5
    ProfilWindow profilWindow;              // 6
    CalendarWindow calendarWindow;          // 7
    TrainingListWindow trainingListWindow;  // 8
    InputTrainingWindow inputTrainingWindow; // 9
    ListWindow listFragment_TrainingCreating;//11
    InputWindow inputWindow;                 //12
    ListSelectWindow listSelectWindow;
    AddTrainingWindow addTrainingWindow; //12
    ChooseExerciseWindow chooseExerciseWindow;//13
    ChooseExerciseDetails chooseExerciseDetails;//14
    TrainingDetails trainingDetails;//15
    public FragmertsHolder(Context context){
        this.context = context;

        startWindow = new StartWindow();
        topControlPanel = new TopControlPanel();
        listFragment_Workout = new ListWindow();
       // listFragment_Meals=new ListWindow();
        listFragment_TrainingCreating=new ListWindow();
        profilWindow = new ProfilWindow();
        exerciseWindow = new ExerciseWindow();
        calendarWindow = new CalendarWindow();
        trainingListWindow=new TrainingListWindow();
        trainingDetails=new TrainingDetails();
        inputTrainingWindow = new InputTrainingWindow();
        inputWindow = new InputWindow();
        addTrainingWindow=new AddTrainingWindow();
        chooseExerciseWindow=new ChooseExerciseWindow();
        chooseExerciseDetails=new ChooseExerciseDetails();

        listSelectWindow = new ListSelectWindow();
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
                return startWindow;
            case 2:
                return topControlPanel;
            case 3:
                return listFragment_Workout;
            case 4:
               // return listFragment_Meals;
            case 5:
                return exerciseWindow;
            case 6:
                return profilWindow;
            case 7:
                return calendarWindow;
            case 8:
                return trainingListWindow;
            case 9:
                return inputTrainingWindow;
            case 10:
                return inputWindow;
            case 11:
                return listFragment_TrainingCreating;
            case 16:
                return listSelectWindow;
            case 12:
                return addTrainingWindow;
            case 13:
                return chooseExerciseWindow;
            case 14:
                return chooseExerciseDetails;
            case 15:
                return trainingDetails;

        }
        return null;
    }

    public void setInputFragment(int index) {

                inputWindow.setInputTag(getTag(index));

    }

    public Series addSeries(){
        return inputWindow.getSeries();
    }


    //Clear Frame to clear space
    public void destroyStartFragment(){this.startWindow = null;}
    public void destroyTopControlPanel(){this.topControlPanel = null;}
    public void destroyExerciseWindow(){this.exerciseWindow = null;}
    public void destroyListFragment_Workout(){this.listFragment_Workout = null;}
   // public void destroyListFragment_Meals(){this.listFragment_Meals = null;}
    public void destroyProfilWindow(){this.profilWindow = null;}
    public void destroyTrainingListWindow(){this.trainingListWindow=null;}
    public void destroyInputTrainingWindow(){this.inputTrainingWindow=null;}
    public void destroyInputWindow(){this.inputWindow=null;}

    private int getTag(int resorce) {
        return context.getResources().getInteger(resorce);
    }

}
