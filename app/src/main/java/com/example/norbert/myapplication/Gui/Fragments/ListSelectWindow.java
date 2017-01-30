package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
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

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Objects.Series;
import com.example.norbert.myapplication.Engin.Objects.Training;
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
import com.example.norbert.myapplication.Engin.Repository.TrainingRepository;
import com.example.norbert.myapplication.Gui.Adapters.IconText_Adp;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;

public class ListSelectWindow extends Fragment {


    // GUI

    ListView list;
    EditText weig,
            repe;
    Button add;

    // DATABASE

    ArrayList<Exercise> exerciseArrayList;
    ExerciseRepository exerciseRepository;
    DatabaseOperations databaseOperations;
    Exercise exerciseTemp;

    //Adapters
    IconText_Adp adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_listselect,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       repe = (EditText)view.findViewById(R.id.ls_repe);
        weig = (EditText)view.findViewById(R.id.ls_weig);
        add =(Button)view.findViewById(R.id.ls_add);
        exerciseArrayList = new ArrayList<Exercise>();

        exerciseRepository = new ExerciseRepository();
        databaseOperations = new DatabaseOperations(view.getContext());
        exerciseArrayList = exerciseRepository.getAllExercise(databaseOperations);

        list = (ListView)view.findViewById(R.id.ls_listview);
        adapter = new IconText_Adp(view.getContext(),exerciseArrayList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setExerciseTemp(adapter.getItem(position));

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ((MainActivity)getActivity()).setSeries(new Series(
                            Integer.parseInt(repe.getText().toString()),
                            Float.parseFloat(weig.getText().toString()),
                            exerciseTemp.getID()
                    ));}catch (Exception e){
                    Log.e("InputWindow",e.getMessage().toString());}
                ((MainActivity) getActivity()).setInputType(R.integer.inputTrainingWindow);
                ((MainActivity) getActivity()).fragmentReplace(R.id.Main,R.integer.inputTrainingWindow);
            }
        });

    }

    public void setExerciseTemp(Exercise exerciseTemp) {
        this.exerciseTemp = exerciseTemp;
    }




}
