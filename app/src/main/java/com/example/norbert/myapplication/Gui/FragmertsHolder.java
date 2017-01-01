package com.example.norbert.myapplication.Gui;



import com.example.norbert.myapplication.Gui.Fragments.ListWindow;
import com.example.norbert.myapplication.Gui.Fragments.StartWindow;

public class FragmertsHolder {

    //Hold which fragment we use right now
    private int index;

    //List of fragments for app
    StartWindow startFragment;
    ListWindow listFragment;

    public FragmertsHolder(int i){
        this.index = i;
        switch (index){
            case 0:
                startFragment = new StartWindow();
                break;
            case 1:
                listFragment = new ListWindow();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }


    // Get The Fragment and give to MainWindow
    public StartWindow getStartFragment(){return startFragment;}
    public ListWindow getListFragment(){return listFragment;}


    //Clear Frame to clear space
    public void destroyStartFragment(){this.startFragment = null;}
    public void destroyListFragment(){this.listFragment = null;}
}
