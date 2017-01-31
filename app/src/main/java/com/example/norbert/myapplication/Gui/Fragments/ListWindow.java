package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.norbert.myapplication.Gui.Adapters.IconText_Adp;
import com.example.norbert.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ListWindow extends Fragment {


    // GUI
    EditText search_text;
    Button search_button;
    Button create_new;
    ListView list;
    List<Exercise> listaCwiczen;
    ArrayList<HashMap<String, String>> exercisesList;



    Context ctx;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize(view);
        initializeAdapter();

    }
    public void initialize(View view){
        search_text = (EditText) view.findViewById(R.id.list_search);
        search_button = (Button)view.findViewById(R.id.list_search_button);
        create_new = (Button)view.findViewById(R.id.list_add_exercise);
        list = (ListView)view.findViewById(R.id.list_listview);

        ctx=getActivity();
        final ExerciseRepository ExerciseRepository = new ExerciseRepository();
        final DatabaseOperations DB = new DatabaseOperations(ctx);
        listaCwiczen =  ExerciseRepository.getAllExercise(DB);



        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).fragmentReplace(R.id.Main,R.integer.inputWindow);
            }
        });
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.setAdapter(null);
                initializeAdapter();
                search_button.setEnabled(false);
                search_text.setText("");


            }
        });
        search_text.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                if(!search_text.getText().toString().matches("")) {
                    list.setAdapter(null);
                    exercisesList = new ArrayList<HashMap<String, String>>();
                    for (int i = 0; i < listaCwiczen.size(); i++) {
                        HashMap<String, String> exer = new HashMap<String, String>();
                        if(listaCwiczen.get(i).getNazwa().toLowerCase().contains(search_text.getText().toString().toLowerCase())) {
                            String name = "Nazwa: " + listaCwiczen.get(i).getNazwa();

                            String desc = "Opis: " + listaCwiczen.get(i).getOpis();
                            String instr = "Instrukcja: " + listaCwiczen.get(i).getInstrukcje();
                            exer.put("name", name);
                            exer.put("desc", desc);
                            exer.put("instr", instr);

                            exercisesList.add(exer);
                            ListAdapter adapter = new SimpleAdapter(
                                    getActivity().getApplicationContext(), exercisesList,
                                    R.layout.adapter_exercises, new String[]{"name", "desc", "instr"}, new int[]{R.id.adap_exer_list_name,
                                    R.id.adap_exer_list_desc, R.id.adap_exer_list_instr});

                            list.setAdapter(adapter);
                        }

                    }
                    if(exercisesList.size()==0)
                        Toast.makeText(getActivity(), "No exercises found",
                                Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Found "+exercisesList.size()+" exercises",
                                Toast.LENGTH_SHORT).show();
                    search_button.setEnabled(true);




                }


            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                listaCwiczen.size();
                ((MainActivity)getActivity()).setPassedExercise(listaCwiczen.get(position));
                ((MainActivity)getActivity()).fragmentReplace(R.id.Main,R.integer.exerciseWindow);
            }
        });




    }
    public void initializeAdapter() {
        ArrayList<HashMap<String, String>> exercisesList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < listaCwiczen.size(); i++) {
            HashMap<String, String> exer = new HashMap<String, String>();
            String name = "Nazwa: " + listaCwiczen.get(i).getNazwa();
            String desc = "Opis: " + listaCwiczen.get(i).getOpis();
            String instr = "Instrukcja: " + listaCwiczen.get(i).getInstrukcje();
            exer.put("name", name);
            exer.put("desc", desc);
            exer.put("instr", instr);

            exercisesList.add(exer);
            ListAdapter adapter = new SimpleAdapter(
                    getActivity().getApplicationContext(), exercisesList,
                    R.layout.adapter_exercises, new String[]{"name", "desc", "instr"}, new int[]{R.id.adap_exer_list_name,
                    R.id.adap_exer_list_desc, R.id.adap_exer_list_instr});

            list.setAdapter(adapter);

        }
    }




}
