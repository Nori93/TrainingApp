package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Objects.Series;
import com.example.norbert.myapplication.Engin.Objects.Training;
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
import com.example.norbert.myapplication.Engin.Repository.TrainingRepository;
import com.example.norbert.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AddTrainingWindow extends Fragment {
    Context ctx;
    EditText nameText,descText;
    Button backToList,addToSeries,saveTraining;
    ListView listOfCreatedSeries;
    ArrayList<HashMap<String, String>> createdSeriesList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_training,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        initializeAdapter();



    }


    public void initialize(View view){
        backToList=(Button)view.findViewById(R.id.add_tr_back);
        addToSeries=(Button)view.findViewById(R.id.add_tr_add_serie);
        saveTraining=(Button)view.findViewById(R.id.add_tr_save_tr);
        nameText=(EditText)view.findViewById(R.id.add_tr_name);
        descText=(EditText)view.findViewById(R.id.add_tr_desc);
        listOfCreatedSeries=(ListView)view.findViewById(R.id.add_tr_list);
        ctx= this.getActivity();

        nameText.setText(((MainActivity)getActivity()).getActualTrainingName());
        descText.setText(((MainActivity)getActivity()).getActualTrainingDesc());


        createdSeriesList = new ArrayList<HashMap<String, String>>();






        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameText.setText("");
                descText.setText("");


                ((MainActivity)getActivity()).clearPassedExercise();
                ((MainActivity)getActivity()).clearHelpingList();

                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.trainingListWindow);
            }
        });
        addToSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.chooseExerciseWindow);
            }
        });
        saveTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nameText.getText().toString().matches("")&&!descText.getText().toString().matches("")&&((MainActivity)getActivity()).getHelpingList().size()!=0)
                {
                    String name=nameText.getText().toString();
                    String desc=descText.getText().toString();
                    List<Series> lista=((MainActivity)getActivity()).getHelpingList();
                    String data=((MainActivity)getActivity()).getData();

                    Training nowy = new Training(null,data,lista,name,desc);


                    final TrainingRepository TrainingRepository= new TrainingRepository();
                    final DatabaseOperations DB = new DatabaseOperations(ctx);
                    TrainingRepository.insertNewTrening(nowy,DB);
                    ((MainActivity)getActivity()).clearPassedExercise();
                    ((MainActivity)getActivity()).clearHelpingList();

                    nameText.setText("");
                    descText.setText("");
                    ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.trainingListWindow);

                }

            }
        });
    }
    public void initializeAdapter(){
        if(((MainActivity)getActivity()).getHelpingList().size()!=0)
        {
            for (int i=0;i<((MainActivity)getActivity()).getHelpingList().size();i++)
            {
                HashMap<String, String> exerInSerie = new HashMap<String, String>();
                Series aktualne =((MainActivity)getActivity()).getHelpingList().get(i);
                String name=((MainActivity)getActivity()).getPassedExercise().getNazwa();
                String desc=((MainActivity)getActivity()).getPassedExercise().getOpis();
                String instr=((MainActivity)getActivity()).getPassedExercise().getInstrukcje();
                String repeats="Powtórzenia: "+String.valueOf(aktualne.getRepeats());
                String weights="Obciążenie: "+String.valueOf(aktualne.getWeights());

                exerInSerie.put("name",name);
                exerInSerie.put("desc",desc);
                exerInSerie.put("instr",instr);
                exerInSerie.put("repeats",repeats);
                exerInSerie.put("weights",weights);

                createdSeriesList.add(exerInSerie);
                ListAdapter adapter = new SimpleAdapter(
                        getActivity().getApplicationContext(), createdSeriesList,
                        R.layout.adapter_series, new String[]{"name", "desc","instr","repeats","weights"}, new int[]{R.id.adap_ser_list_name,
                        R.id.adap_ser_list_desc,R.id.adap_ser_list_instr,R.id.adap_ser_list_repeats,R.id.adap_ser_list_weights});

                listOfCreatedSeries.setAdapter(adapter);

            }
        }
    }
}
