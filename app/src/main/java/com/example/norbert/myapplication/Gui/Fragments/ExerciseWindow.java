package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.R;

/***
 *
 * FRAGMENT Exercise INFO:
 * - have All information about exercise
 * - A list of the best exercise
 * - Button to go back to list of exercise
 * - Button to start the exercise
 * - Button to add this exercise to training
 */
public class ExerciseWindow extends Fragment {

    Exercise exercise;
    TextView title,
            desc,
            inst;

    ImageView img;

    Button  back,
            start,
            add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = (TextView)view.findViewById(R.id.exer_title);
        desc = (TextView)view.findViewById(R.id.exer_desc);
        inst = (TextView)view.findViewById(R.id.exer_inst);

        img = (ImageView)view.findViewById(R.id.exer_icon);

        back =(Button)view.findViewById(R.id.exer_but_back);
        start =(Button)view.findViewById(R.id.exer_but_start);
        add =(Button)view.findViewById(R.id.exer_but_add);

        exercise = ((MainActivity)getActivity()).getListSelected();

        title.setText(exercise.getNazwa());
        desc.setText(exercise.getOpis());
        inst.setText(exercise.getInstrukcje());
    }

    private void Buttons(){

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
