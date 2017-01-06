package com.example.norbert.myapplication.Gui.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.Repository.InformationRepository;
import com.example.norbert.myapplication.R;

public class StartWindow extends Fragment {


    EditText Test1;
    Button buttonTest,buttonGet;
    Context ctx;
    InformationRepository InformationRepository;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start_window,container, false);
        ctx= this.getActivity();
        InformationRepository = new InformationRepository();

       Test1 = (EditText)  view.findViewById(R.id.editText2);
        buttonTest = (Button) view.findViewById(R.id.button);

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseOperations DB = new DatabaseOperations(ctx);
                InformationRepository.PutInformationData(DB,123);

            }
        });

        buttonGet = (Button) view.findViewById(R.id.button3);

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOperations DB = new DatabaseOperations(ctx);

                Cursor CR =  InformationRepository.getInformation(DB);
                CR.moveToFirst();

                do{
                    Log.d("Dane z bazy" , CR.getString(0)+" "+CR.getString((1)));
                }while(CR.moveToNext());

            }
        });





        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
