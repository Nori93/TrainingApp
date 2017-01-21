package com.example.norbert.myapplication.Gui.Fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Gui.FragmertsHolder;
import com.example.norbert.myapplication.R;

public class StartWindow extends Fragment {

    private FragmentManager manager;                                                                //

    private FragmentTransaction transaction;
    private FragmertsHolder holder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_window,container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity)getActivity()).setCalendar();

    }

}
