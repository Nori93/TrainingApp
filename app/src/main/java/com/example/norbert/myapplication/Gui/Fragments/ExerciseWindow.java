package com.example.norbert.myapplication.Gui.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;


public class ExerciseWindow extends Fragment {

    Context context;
    Button back,delete;
    TextView name,desc,instr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise,container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);


    }
    public void initialize(View view){
        context = this.getActivity();
        back=(Button)view.findViewById(R.id.exerc_back);
        delete=(Button)view.findViewById(R.id.exer_delete);
        name=(TextView)view.findViewById(R.id.exer_name);
        desc=(TextView)view.findViewById(R.id.exer_desc);
        instr=(TextView)view.findViewById(R.id.exer_instr);

        name.setText(((MainActivity)getActivity()).getPassedExercise().getNazwa());
        desc.setText(((MainActivity)getActivity()).getPassedExercise().getOpis());
        instr.setText(((MainActivity)getActivity()).getPassedExercise().getInstrukcje());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).clearPassedExercise();
                ((MainActivity) getActivity()).fragmentReplace(R.id.Main,R.integer.listFragment_Workout);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete Exercise")
                        .setMessage("Are you sure you want to delete this exercise?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Integer id=((MainActivity)getActivity()).getPassedExercise().getID();

                                final ExerciseRepository exerciseRepository = new ExerciseRepository();
                                final DatabaseOperations DB = new DatabaseOperations(context);
                                exerciseRepository.deleteExerciseByID(id,DB);
                                ((MainActivity)getActivity()).clearPassedExercise();
                                ((MainActivity) getActivity()).fragmentReplace(R.id.Main,R.integer.listFragment_Workout);



                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });


    }


}
