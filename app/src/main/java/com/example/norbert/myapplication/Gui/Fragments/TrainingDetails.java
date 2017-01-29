package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Objects.Training;
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
import com.example.norbert.myapplication.Engin.Repository.TrainingRepository;
import com.example.norbert.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TrainingDetails extends Fragment {

    Context ctx;
    TextView nameText,descText,dateText;
    ListView seriesList;
    Button backToTrList,deleteTraining;
    Training training;
    Exercise exercise;
    ArrayList<HashMap<String, String>> seriesHashList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.training_details,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        seriesHashList = new ArrayList<HashMap<String, String>>();

        initializing(view);


        //Toast.makeText(getActivity().getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();

    }
    public void initializing(View view)
    {
        seriesList=(ListView)view.findViewById(R.id.tr_det_list);
        backToTrList=(Button)view.findViewById(R.id.tr_det_back);
        deleteTraining=(Button)view.findViewById(R.id.tr_det_delete);
        nameText=(TextView)view.findViewById(R.id.tr_det_name);
        descText=(TextView)view.findViewById(R.id.tr_det_desc);
        dateText=(TextView)view.findViewById(R.id.tr_det_date);
        training=((MainActivity)getActivity()).getPassedTraining();

        nameText.setText("Nazwa treningu: "+training.getNazwa());
        descText.setText("Opis treningu: "+training.getOpis());
        dateText.setText("Planowana data wykonania: "+training.getData());

        for(int i=0;i<training.getSerie().size();i++)
        {
            ctx= this.getActivity();
            final ExerciseRepository ExerciseRepository = new ExerciseRepository();
            final DatabaseOperations DB = new DatabaseOperations(ctx);
            exercise =  ExerciseRepository.getExerciseById(training.getSerie().get(i).getId_cw(),DB); // Dostajesz cały wypełniony obiekt!

            HashMap<String, String> exerInSerie = new HashMap<String, String>();
            String name=exercise.getNazwa();
            String desc=exercise.getOpis();
            String instr=exercise.getInstrukcje();
            String repeats="Powtórzenia: "+String.valueOf(training.getSerie().get(i).getRepeats());
            String weights="Obciążenie: "+String.valueOf(training.getSerie().get(i).getWeights());

            exerInSerie.put("name",name);
            exerInSerie.put("desc",desc);
            exerInSerie.put("instr",instr);
            exerInSerie.put("repeats",repeats);
            exerInSerie.put("weights",weights);

            seriesHashList.add(exerInSerie);
            ListAdapter adapter = new SimpleAdapter(
                    getActivity().getApplicationContext(), seriesHashList,
                    R.layout.adapter_series, new String[]{"name", "desc","instr","repeats","weights"}, new int[]{R.id.adap_ser_list_name,
                    R.id.adap_ser_list_desc,R.id.adap_ser_list_instr,R.id.adap_ser_list_repeats,R.id.adap_ser_list_weights});

            seriesList.setAdapter(adapter);
        }


        backToTrList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.trainingListWindow);
                Fragment workout = new ListWindow();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.Main,workout);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        deleteTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TrainingRepository TrainingRepository = new TrainingRepository();
                final DatabaseOperations DB = new DatabaseOperations(ctx);
                TrainingRepository.DeleteExistingTraining(training.getID(),DB);
                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.trainingListWindow);
            }
        });
    }




}
