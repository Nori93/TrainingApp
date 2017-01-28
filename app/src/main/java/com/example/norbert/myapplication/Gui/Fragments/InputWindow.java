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
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
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



    }



}
