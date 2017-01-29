package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Objects.Training;
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
public class TrainingWindow extends Fragment {

    Training training;
    TextView title,
            desc,
            inst;

    ImageView img;

    Button  back,
            start,
            add;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this.getActivity();
        title = (TextView)view.findViewById(R.id.exer_title);
        desc = (TextView)view.findViewById(R.id.exer_desc);
        inst = (TextView)view.findViewById(R.id.exer_inst);

        img = (ImageView)view.findViewById(R.id.exer_icon);

        back =(Button)view.findViewById(R.id.exer_but_back);
        start =(Button)view.findViewById(R.id.exer_but_start);
        add =(Button)view.findViewById(R.id.exer_but_add);

        training = ((MainActivity)getActivity()).getListSelectedTr();

        title.setText(training.getNazwa());
        desc.setText(training.getOpis());

        Buttons(view);
    }

    private void Buttons(View view){

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
