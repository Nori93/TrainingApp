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
import com.example.norbert.myapplication.Engin.Repository.SeriesRepository;
import com.example.norbert.myapplication.Engin.Repository.TrainingRepository;
import com.example.norbert.myapplication.Gui.Adapters.EditText_Adp;
import com.example.norbert.myapplication.Gui.Adapters.IconText_Adp;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;

public class InputWindow extends Fragment {


    // GUI

    Button save;
    ListView list;

    // DATABASE
    DatabaseOperations databaseOperations;
    ExerciseRepository exerciseRepository;
    TrainingRepository trainingRepository;
    SeriesRepository seriesRepository;

    int tag;
    String[] rows_inputs;

    //Adapters
    EditText_Adp editText_adp;

    public void setInputTag(int t){
        this.tag = t;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_input,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        save = (Button)view.findViewById(R.id.input__frag_save);
        list = (ListView)view.findViewById(R.id.input_frag_list);
        list.setClickable(false);
        editText_adp = new EditText_Adp(view.getContext(),this.tag);
        list.setAdapter(editText_adp);



        databaseOperations = new DatabaseOperations(view.getContext());
        exerciseRepository = new ExerciseRepository();
        trainingRepository = new TrainingRepository();
        seriesRepository = new SeriesRepository();
        Save();

    }
    private void Save(){
        switch (tag) {
            case 51:
              //{"id","Name","Description","Instruction","Icon Path"};
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rows_inputs = editText_adp.getAllData();
                        exerciseRepository.insertNewExcerise(new Exercise(rows_inputs[0],rows_inputs[1],rows_inputs[2],rows_inputs[3]),databaseOperations);
                    }
                });
                break;
            case 52:
                // {"id","Repeats", "Weights", "ID Exercise", "ID Training"};
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rows_inputs = editText_adp.getAllData();

                    }
                });
                break;
            case 53:
                // {"id","Name","Description","Data"};
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rows_inputs = editText_adp.getAllData();
                    trainingRepository.insertNewTrening(new Training(rows_inputs[0],rows_inputs[1]),databaseOperations);
                    }
                });
                break;
            case 54:
               //{"Weight","Height", "Calories", "Activity","Fat", "Carb", "Protein", "Target","Body Type", "Sex","Age"};
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
        }
    }


    public Series getSeries() {
        return new Series(
                Integer.parseInt(rows_inputs[0]),
                Float.parseFloat(rows_inputs[1]),
                Integer.parseInt(rows_inputs[2]),
                Integer.parseInt(rows_inputs[3])
        );
    }
}
