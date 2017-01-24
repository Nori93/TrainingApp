package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.R;


public class ExerciseWindow extends Fragment {

    Exercise exercise;
    TextView title,
            desc,
            inst;

    ImageView img;

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

        exercise = ((MainActivity)getActivity()).getListSelected();

        title.setText(exercise.getNazwa());
        desc.setText(exercise.getOpis());
        inst.setText(exercise.getInstrukcje());
    }
}
