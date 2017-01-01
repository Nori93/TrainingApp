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
    private int fragmentId = 0;                                                                     //The Id of fragment that is now on Main Window
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

    public void setMainWindow(){
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        holder = new FragmertsHolder(fragmentId);

        switch (fragmentId){
            //StartWindows Fragment
             case 0:
                //holder.getStartFragment() <give some informations>
                transaction.add(R.id.Main,holder.getStartFragment());
                transaction.add(R.id.Top,holder.getTopControlPanel());
                transaction.commit();
                break;
        }
    }

    public void changeScene(int id){
        switch (fragmentId){
            //StartWindows Fragment
            case 0:
                holder.destroyStartFragment();
                transaction.remove(holder.getStartFragment());
                break;
        }
        this.fragmentId = id;
        setMainWindow();
    }


}
