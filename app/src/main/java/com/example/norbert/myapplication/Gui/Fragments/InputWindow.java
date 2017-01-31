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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Objects.Series;
import com.example.norbert.myapplication.Engin.Objects.Training;
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
import com.example.norbert.myapplication.Engin.Repository.SeriesRepository;
import com.example.norbert.myapplication.Engin.Repository.TrainingRepository;
import com.example.norbert.myapplication.Gui.Adapters.EditText_Adp;
import com.example.norbert.myapplication.Gui.Adapters.IconText_Adp;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;

public class InputWindow extends Fragment {


    // GUI

    Button save,back;
    EditText editName,editDesc,editInstr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_input,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initialize(view);

    }
    public void initialize(View view){
        save = (Button)view.findViewById(R.id.input_save);
        back=(Button)view.findViewById(R.id.input_back);
        editDesc=(EditText)view.findViewById(R.id.input_descr);
        editInstr=(EditText)view.findViewById(R.id.input_instr);
        editName=(EditText)view.findViewById(R.id.input_name);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).fragmentReplace(R.id.Main,R.integer.listFragment_Workout);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editDesc.getText().toString().matches("")&&!editName.getText().toString().matches("")&&!editInstr.getText().toString().matches("")) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Creating Exercise")
                            .setMessage("Are you sure you want to create this exercise?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String name = editName.getText().toString();
                                    String desc = editDesc.getText().toString();
                                    String instr = editInstr.getText().toString();

                                    editName.setText("");
                                    editInstr.setText("");
                                    editDesc.setText("");
                                    Exercise cwiczenie = new Exercise(0,name,desc,instr,"");
                                    Context ctx=getActivity();
                                    final ExerciseRepository exerciseRepository = new ExerciseRepository();
                                    final DatabaseOperations DB = new DatabaseOperations(ctx);
                                    exerciseRepository.insertNewExcerise(cwiczenie,DB);
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
                else
                Toast.makeText(getActivity(), "Fill all inputs",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

}
