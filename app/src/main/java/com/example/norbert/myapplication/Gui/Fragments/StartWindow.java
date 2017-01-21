package com.example.norbert.myapplication.Gui.Fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.norbert.myapplication.Gui.FragmertsHolder;
import com.example.norbert.myapplication.R;

public class StartWindow extends Fragment {

    private FragmentManager manager;                                                                //

    private FragmentTransaction transaction;
    private FragmertsHolder holder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil_window,container, false);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCalendar();

    }
    private void setCalendar() {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        holder = new FragmertsHolder();
        transaction.add(R.id.fragment_calendar_window, holder.getCalendarWindow());
        transaction.commit();
    }
}
