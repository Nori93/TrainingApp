package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
import com.example.norbert.myapplication.Gui.Adapters.IconText_Adp;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;

public class ListWindow extends Fragment {


    // GUI
    TextView search_text;
    Button search_button;
    ListView list;

    // DATABASE
    DatabaseOperations databaseOperations;
    ExerciseRepository exerciseRepository;
    ArrayList<Exercise> exerciseArrayList;

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
        databaseOperations = new DatabaseOperations(view.getContext());
        exerciseRepository = new ExerciseRepository();
        exerciseArrayList = exerciseRepository.getAllExercise(databaseOperations);


        search_text = (TextView)view.findViewById(R.id.list_search);
        search_button = (Button)view.findViewById(R.id.list_button);
        list = (ListView)view.findViewById(R.id.list_listview);
        adapter = new IconText_Adp(view.getContext(),exerciseArrayList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity) getActivity()).setListSelected(adapter.getItem(position));
                ((MainActivity) getActivity()).goToExerciseDesc();
            }
        });
    }


}
