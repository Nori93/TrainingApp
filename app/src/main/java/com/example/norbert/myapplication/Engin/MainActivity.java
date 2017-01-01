package com.example.norbert.myapplication.Engin;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.norbert.myapplication.Gui.FragmertsHolder;
import com.example.norbert.myapplication.R;

public class MainActivity extends Activity {

    //Main Window is a container for all fragments
    private RelativeLayout mainWindow;



    //Fragment tools
    private int fragmentId = 1;                                                                     //The Id of fragment that is now on Main Window
    private FragmentManager manager;                                                                //
    private FragmentTransaction transaction;
    private FragmertsHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainWindow = (RelativeLayout)findViewById(R.id.Main);
        setMainWindow();

    }

    public void setMainWindow() {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        holder = new FragmertsHolder();
        transaction.add(R.id.Top, holder.getTopControlPanel());
        transaction.add(R.id.Main,holder.getStartFragment());
        transaction.commit();

    }
    public void setBodyWindow(){
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        switch (fragmentId){
            //StartWindows Fragment
             case 1:
                //holder.getStartFragment() <give some informations>
                transaction.replace(R.id.Main,holder.getStartFragment());
                transaction.commit();
                break;
            //Exercise Fragment
            case 2:
                //holder.getStartFragment() <give some informations>
                transaction.replace(R.id.Main,holder.getListFragment_Workout());
                transaction.commit();
                break;
            //Meals Fragment
            case 3:
                //holder.getStartFragment() <give some informations>
                transaction.replace(R.id.Main,holder.getListFragment_Meals());
                transaction.commit();
                break;
            //Profile Fragment
            case 4:

                break;

        }
    }

    public void changeScene(int id){
        this.fragmentId = id;
        setBodyWindow();
    }


}
