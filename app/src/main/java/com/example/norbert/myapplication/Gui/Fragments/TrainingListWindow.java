package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.R;


public class TrainingListWindow extends Fragment {

    ListView lv;
    Button addTrainingButt;
    Button backToCalendarButt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_training_list,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize(view);
    }
    public void initialize(View view)
    {
        lv=(ListView)view.findViewById(R.id.frtrli_list);
        addTrainingButt=(Button)view.findViewById(R.id.frtrli_add_button);
        backToCalendarButt=(Button)view.findViewById(R.id.frtrli_back_button);

        backToCalendarButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).backtoCalendar();
            }
        });
        addTrainingButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).goToAddTraining();
            }
        });
    }
}
