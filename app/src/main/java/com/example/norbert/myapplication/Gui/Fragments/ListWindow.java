package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
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
import com.example.norbert.myapplication.Engin.Objects.Training;
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
import com.example.norbert.myapplication.Engin.Repository.TrainingRepository;
import com.example.norbert.myapplication.Gui.Adapters.IconText_Adp;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;

public class ListWindow extends Fragment {


    // GUI
    TextView search_type;
    EditText search_text;
    Button search_button;
    Button create_new;
    ListView list;

    //Type
    Boolean type = true;

    // DATABASE
    DatabaseOperations databaseOperations;
    ExerciseRepository exerciseRepository;
    ArrayList<Exercise> exerciseArrayList;
    TrainingRepository trainingRepository;
    ArrayList<Training> trainingArrayList;

    //Adapters
    IconText_Adp adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        exerciseArrayList = new ArrayList<Exercise>();
        trainingArrayList = new ArrayList<Training>();
        databaseOperations = new DatabaseOperations(view.getContext());
        exerciseRepository = new ExerciseRepository();
        trainingRepository = new TrainingRepository();

        exerciseArrayList = exerciseRepository.getAllExercise(databaseOperations);
        trainingArrayList = trainingRepository.getAllTraining(databaseOperations);

        search_type = (TextView) view.findViewById(R.id.list_typeOfList);

        search_text = (EditText) view.findViewById(R.id.list_search);
        search_button = (Button)view.findViewById(R.id.list_button);
        create_new = (Button)view.findViewById(R.id.list_Create);
        list = (ListView)view.findViewById(R.id.list_listview);
        changeName(view);


        search_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = !type;
                changeName(v);
            }
        });


        if(type){


        }
        else {


        }
    }

    private void changeName(View view) {
        if (type){
            search_type.setText("Exercise");
            adapter = new IconText_Adp(view.getContext(),exerciseArrayList);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //get target exercise
                    ((MainActivity) getActivity()).setListSelected(adapter.getItem(position));
                    // start fragment exercise
                    ((MainActivity) getActivity()).fragmentReplace(R.id.Main,R.integer.exerciseWindow);
                }
            });

            create_new.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) getActivity()).setInputType(R.integer.Exercise);
                    ((MainActivity) getActivity()).fragmentReplace(R.id.Main,R.integer.inputWindow);
                }
            });
        }

        else{
            search_type.setText("Training");
            adapter = new IconText_Adp(view.getContext(),trainingArrayList,false);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //get target exercise
                    ((MainActivity) getActivity()).setListSelected(adapter.getItem(position));
                    // start fragment exercise
                    ((MainActivity) getActivity()).fragmentReplace(R.id.Main,R.integer.exerciseWindow);
                }
            });

            create_new.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) getActivity()).setInputType(R.integer.Training);
                    ((MainActivity) getActivity()).fragmentReplace(R.id.Main,R.integer.inputWindow);
                }
            });

        }
    }
    private void setTraningList(){
        String[] temp = new String[trainingArrayList.size()];
        for (int i=0;i<temp.length;i++)
        {
         temp[i]=trainingArrayList.get(i).getNazwa();
        }
        ((MainActivity)getActivity()).setTraningDB(temp);
    }


}
