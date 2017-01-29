package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Series;
import com.example.norbert.myapplication.Engin.Objects.Training;
import com.example.norbert.myapplication.Engin.Repository.TrainingRepository;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;

public class InputTrainingWindow extends Fragment {


    // GUI

    EditText name;
    EditText data;
    EditText desc;

    Button add;
    Button save;

    ListView list;

    // DATABASE
    DatabaseOperations databaseOperations;
    TrainingRepository trainingRepository;

    Training training;

    ArrayList<Series> seriesArrayList;
    private View views;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_inputtraining,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.views = view;
        name = (EditText)view.findViewById(R.id.trai_name);
        data = (EditText)view.findViewById(R.id.trai_data);
        desc = (EditText)view.findViewById(R.id.trai_desc);

        add = (Button)view.findViewById(R.id.trai_add);
        save = (Button)view.findViewById(R.id.trai_save);
        list = (ListView)view.findViewById(R.id.trai_list);


        databaseOperations = new DatabaseOperations(view.getContext());
        trainingRepository = new TrainingRepository();

        seriesArrayList = new ArrayList<Series>();

        try{
            name.setText(((MainActivity)getActivity()).getTrainingName());
            data.setText(((MainActivity)getActivity()).getTrainingData());
            desc.setText(((MainActivity)getActivity()).getTrainingDesc());

        }catch (Exception e){
            Log.e("InputTrainig",e.getMessage().toString());
        }
        try{
            seriesArrayList = ((MainActivity)getActivity()).getSeries();
        }catch (Exception e){
            Log.e("InputTrainig",e.getMessage().toString());
        }
        try{
        list.setAdapter(setArrauAdapter());
        }catch (Exception e){
            Log.e("InputTrainig",e.getMessage().toString());
        }

        Buttons();
    }

    private ArrayAdapter setArrauAdapter() {
        ArrayList<String> str = new ArrayList<String>();
        for (Series s:seriesArrayList)
        {
         str.add(s.getRepeats() + "");
        }
        ArrayAdapter<String> result = new ArrayAdapter<String>(views.getContext(),str.size(),str);
        return result;
    }

    private void Buttons(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setTrainingName(name.getText().toString());
                ((MainActivity)getActivity()).setTrainingData(data.getText().toString());
                ((MainActivity)getActivity()).setTrainingDesc(desc.getText().toString());
                //((MainActivity) getActivity()).setInputType(R.integer.Series);
                ((MainActivity) getActivity()).fragmentReplace(R.id.Main,R.integer.listSelectWindow);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainingRepository.insertNewTrening(new Training(
                        ((MainActivity)getActivity()).getTrainingData(),
                        ((MainActivity)getActivity()).getSeries(),
                        ((MainActivity)getActivity()).getTrainingName(),
                        ((MainActivity)getActivity()).getTrainingDesc()),
                        databaseOperations);
            }
        });
    }





}
