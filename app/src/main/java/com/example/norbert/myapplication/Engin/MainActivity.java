package com.example.norbert.myapplication.Engin;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Objects.Series;
import com.example.norbert.myapplication.Engin.Objects.Training;
import com.example.norbert.myapplication.Engin.Sensor.Accelerometer;
import com.example.norbert.myapplication.Engin.Sensor.Gravity;
import com.example.norbert.myapplication.Engin.Sensor.LinearAccelerometer;
import com.example.norbert.myapplication.Gui.FragmertsHolder;
import com.example.norbert.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    //Sensors
    SensorManager sensorManager;
    Accelerometer accelerometer;
    Gravity gravity;
    LinearAccelerometer linearAccelerometer;
    Training passedTraining;

    //Main Window is a container for all fragments
    private RelativeLayout mainWindow;

    //Calendar tools
    String data;
    String[] traningDB;
    ArrayList<Series> helpingList;



    //Fragment tools
    private int fragmentId = 1;                                                                     //The Id of fragment that is now on Main Window
    private FragmentManager manager;                                                                //
    private FragmentTransaction transaction;
    private FragmertsHolder holder;


    // List Window
    Exercise exercise;
    Exercise passedExercise;
    Training training;

    //Series
    String trName;
    String trDesc;
    String trData;
    ArrayList<Series>serieslist = new ArrayList<Series>();



    public int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainWindow = (RelativeLayout)findViewById(R.id.Main);
        setMainWindow();

        //Sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = new Accelerometer(sensorManager);
        gravity = new Gravity(sensorManager);
        linearAccelerometer = new LinearAccelerometer(sensorManager);
        helpingList=new ArrayList<>();
    }

    public void setMainWindow() {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        holder = new FragmertsHolder(this);
        transaction.add(R.id.Top, holder.getFragment(getTag(R.integer.topControlPanel)));
        transaction.add(R.id.Main,holder.getFragment(getTag(R.integer.startWindow)));
        transaction.commit();

    }
    public void setBodyWindow(){
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        holder = new FragmertsHolder(this);

        switch (fragmentId){
            //StartWindows Fragment
             case 1:

                 clearAll();
                 flag=0;
                 //holder.setStartWindow();
                transaction.replace(R.id.Main,holder.getFragment(getTag(R.integer.startWindow)));
                transaction.commit();
                break;
            //Workout List Fragment
            case 2:
                clearAll();
                flag=1;
                //holder.getStartFragment() <give some informations>
                transaction.replace(R.id.Main,holder.getFragment(getTag(R.integer.listFragment_Workout)));
                transaction.commit();
                break;
            //Meals Fragment
            case 3:
                //holder.getStartFragment() <give some informations>
                transaction.replace(R.id.Main,holder.getFragment(getTag(R.integer.listFragment_Meals)));
                transaction.commit();
                break;
            //Profile Fragment
            case 4:
                clearAll();
                transaction.replace(R.id.Main,holder.getFragment(getTag(R.integer.profilWindow)));
                transaction.commit();

                break;

        }
        holder.destroyStartFragment();
    }

    public void changeScene(int id){
        this.fragmentId = id;
        setBodyWindow();
    }


    public void loadTrainings(String month)
    {

    }


    // Exercise Transfer
    public Exercise getListSelectedEx() {
        return exercise;
    }
    public void setListSelectedEx(Exercise e){
        this.exercise = e;
    }

    // Traning Transfer
    public Training getListSelectedTr() {  return training;  }
    public void setListSelectedTr(Training t){
        this.training = t;
    }



    public Fragment getFragment(int tag){
      return holder.getFragment(getTag(tag));
    }

    public void fragmentReplace(int where, int tag){
        transaction = manager.beginTransaction();
        transaction.replace(where, holder.getFragment(getTag(tag)));
        transaction.commit();
    }

    public void setData(String rok,String miesiac,String dzien) {
        data=dzien+'-'+miesiac+'-'+rok;
    }
    private int getTag(int resorce) {
        return getResources().getInteger(resorce);
    }
    public String getData()
    {
        return data;
    }

    public void setTraningDB(String[] traningDB) {
        this.traningDB = traningDB;
    }
    public String[] getTraningDB(){
        return traningDB;
    }
    public Training getPassedTraining(){return passedTraining;}
    public void setPassedTraining(Training value){passedTraining=value;}

    public void setSeries(Series s){ serieslist.add(s);}
    public ArrayList<Series> getSeries(){return serieslist;}

    public void setFlag(int flaga){flag=flaga;}
    public int getFlag(){return flag;}

    public void setTrainingName(String n){this.trName= n;}
    public String getTrainingName(){return trName;}
    public void setTrainingDesc(String n){this.trDesc= n;}
    public String getTrainingDesc(){return trDesc;}
    public void setTrainingData(String trainingData) {  this.trData = trainingData;  }
    public String getTrainingData(){return trData;}
    public List<Series> getHelpingList(){return helpingList;}
    public void addHelpingList(Series a){helpingList.add(a);}
    public void clearHelpingList(){helpingList.clear();}
    public void deleteHelpingList(int i){helpingList.remove(i);}
    public void setPassedExercise(Exercise value){passedExercise=value;}
    public Exercise getPassedExercise(){return passedExercise;}
    public void clearPassedExercise(){passedExercise=null;}
    public void clearData(){data=null;}
    public void clearAll(){clearHelpingList();clearPassedExercise();clearData();}

}
