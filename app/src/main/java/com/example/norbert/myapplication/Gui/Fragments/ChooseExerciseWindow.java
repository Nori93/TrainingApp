package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
import com.example.norbert.myapplication.Engin.Repository.TrainingRepository;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChooseExerciseWindow extends Fragment {

    Button backToTrainingCreator;
    ListView listOfExercises;
    Context ctx;
    List<Exercise> listaCwiczen;
    ArrayList<HashMap<String, String>> exerciseList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.choose_exercise_window,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize(view);



    }


    public void initialize(View view){
        backToTrainingCreator=(Button)view.findViewById(R.id.choo_ex_back);
        listOfExercises=(ListView)view.findViewById(R.id.choo_ex_list);

        exerciseList = new ArrayList<HashMap<String, String>>();


        ctx= this.getActivity();
        final ExerciseRepository ExerciseRepository = new ExerciseRepository();
        final DatabaseOperations DB = new DatabaseOperations(ctx);
        listaCwiczen =  ExerciseRepository.getAllExercise(DB);

        backToTrainingCreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.addTrainingWindow);
            }
        });
        listOfExercises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                ((MainActivity)getActivity()).setPassedExercise(listaCwiczen.get(position));
                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.chooseExerciseDetails);
            }
        });

        for(int i=0;i<listaCwiczen.size();i++)
        {
            HashMap<String, String> exer = new HashMap<String, String>();
            String name="Nazwa: "+listaCwiczen.get(i).getNazwa();
            String desc="Opis: "+listaCwiczen.get(i).getOpis();
            String instr="Instrukcja: "+listaCwiczen.get(i).getInstrukcje();
            exer.put("name",name);
            exer.put("desc",desc);
            exer.put("instr",instr);

            exerciseList.add(exer);
            ListAdapter adapter = new SimpleAdapter(
                    getActivity().getApplicationContext(), exerciseList,
                    R.layout.adapter_exercises, new String[]{"name", "desc","instr"}, new int[]{R.id.adap_exer_list_name,
                    R.id.adap_exer_list_desc,R.id.adap_exer_list_instr});

            listOfExercises.setAdapter(adapter);


        }

    }
}
