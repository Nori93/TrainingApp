package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.norbert.myapplication.Engin.Objects.Training;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;

/**
 * Created by norbert on 02.01.2017.
 */

public class WorkoutWindow extends Fragment {

    private boolean inTraining=false; // bool if is a training in list then this is true
    private ArrayList<Training> trainingArrayList; // list of your training

    private ImageButton
            left,
            mid,
            right;

    private ListView
            list;

    private Button
            startTraining;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void Gui(View view){
        left = (ImageButton) view.findViewById(R.id.wo_top_left_image);
        mid = (ImageButton)view.findViewById(R.id.wo_top_mid_image);
        right = (ImageButton)view.findViewById(R.id.wo_top_right_image);

        list = (ListView)view.findViewById(R.id.wo_list);

        startTraining = (Button)view.findViewById(R.id.wo_button);



        // On click Listener
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        startTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //List Adapter

    }
}
