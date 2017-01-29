package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Training;
import com.example.norbert.myapplication.Engin.Objects.UserInformation;
import com.example.norbert.myapplication.Engin.Repository.InformationRepository;
import com.example.norbert.myapplication.Engin.Repository.TrainingRepository;
import com.example.norbert.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.format;


public class TrainingListWindow extends Fragment {

    Context ctx;
    ListView lv;
    Button addTrainingButt;
    Button backToCalendarButt;
    TextView dataTextView;
    List<Training> lista;
    ArrayList<HashMap<String, String>> trainingList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_training_list,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        trainingList = new ArrayList<HashMap<String, String>>();
        initialize(view);
        initializeAdapter();
    }
    public void initialize(View view)
    {
        lv=(ListView)view.findViewById(R.id.frtrli_list);
        addTrainingButt=(Button)view.findViewById(R.id.frtrli_add_button);
        backToCalendarButt=(Button)view.findViewById(R.id.frtrli_back_button);
        dataTextView=(TextView)view.findViewById(R.id.frtrli_data_textview);

        ctx= this.getActivity();
        final TrainingRepository TrainingRepository = new TrainingRepository();
        final DatabaseOperations DB = new DatabaseOperations(ctx);
        lista =  TrainingRepository.GetTrainingByDate(((MainActivity)getActivity()).getData(),DB); // Dostajesz cały wypełniony obiekt!


        dataTextView.setText(((MainActivity)getActivity()).getData());


        backToCalendarButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.calendarWindow);
            }
        });
        addTrainingButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.listFragment_TrainingCreating);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                ((MainActivity)getActivity()).setPassedTraining(lista.get(position));
                //((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.trainingDetails);
            }
        });
    }
    public void initializeAdapter()
    {
        for(int i=0;i<lista.size();i++)
        {
            HashMap<String, String> train = new HashMap<String, String>();
            String name=lista.get(i).getNazwa();
            String desc=lista.get(i).getOpis();
            train.put("name",name);
            train.put("desc",desc);
            trainingList.add(train);
            ListAdapter adapter = new SimpleAdapter(
                    getActivity().getApplicationContext(), trainingList,
                    R.layout.adapter_training_list, new String[]{"name", "desc"}, new int[]{R.id.adap_tr_list_name,
                    R.id.adap_tr_list_desc});

            lv.setAdapter(adapter);
        }

    }
}
