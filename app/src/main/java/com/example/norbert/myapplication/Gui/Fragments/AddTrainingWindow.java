package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class AddTrainingWindow extends Fragment {

    Button backButton,addSeriesButton,finishCreatingTrainingButton;
    ListView seriesCreatedList;
    EditText nameOfTraining;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




//        final DatabaseOperations DB = new DatabaseOperations(getActivity());
//        //tests
//        TrainingRepository trainingRepository = new TrainingRepository();
//        Training tmp =  trainingRepository.GetTrainingById(1,DB);



        return inflater.inflate(R.layout.fragment_add_training,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializing(view);
    }
    public void initializing(View view)
    {
        backButton=(Button)view.findViewById(R.id.addTr_back_button);
        addSeriesButton=(Button)view.findViewById(R.id.addTr_add_series);
        finishCreatingTrainingButton=(Button)view.findViewById(R.id.addTr_finish_button);
        seriesCreatedList=(ListView)view.findViewById(R.id.addTr_list);
        nameOfTraining=(EditText)view.findViewById(R.id.addTr_name);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,8);
            }
        });
        addSeriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setActualTrainingName(nameOfTraining.getText().toString());
                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,3);
            }
        });

    }

}
