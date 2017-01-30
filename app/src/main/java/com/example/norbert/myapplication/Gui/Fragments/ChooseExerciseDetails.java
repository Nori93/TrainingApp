package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Series;
import com.example.norbert.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChooseExerciseDetails extends Fragment {

    TextView name;
    EditText repeatsEdit,weightEdit;
    Button backToExerList,addNewSerie;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.choose_exercise_details,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize(view);

    }


    public void initialize(View view)
    {
        name=(TextView)view.findViewById(R.id.choo_exer_details_name);
        repeatsEdit=(EditText)view.findViewById(R.id.choo_exer_details_repeats);
        weightEdit=(EditText)view.findViewById(R.id.choo_exer_details_weight);
        backToExerList=(Button)view.findViewById(R.id.choo_exer_details_back);
        addNewSerie=(Button)view.findViewById(R.id.choo_exer_details_add);

        name.setText(((MainActivity)getActivity()).getPassedExercise().getNazwa());

        backToExerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).clearPassedExercise();
                repeatsEdit.setText("");
                weightEdit.setText("");
                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.chooseExerciseWindow);
            }
        });
        addNewSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!repeatsEdit.getText().toString().matches("")&&!weightEdit.getText().toString().matches("")) {

                    int rep = Integer.parseInt(repeatsEdit.getText().toString());
                    float weigh = Float.parseFloat(weightEdit.getText().toString());
                    repeatsEdit.setText("");
                    weightEdit.setText("");
                    int id_cw = ((MainActivity) getActivity()).getPassedExercise().getID();
                    ((MainActivity) getActivity()).addHelpingList(new Series(0, rep, weigh, id_cw, 0));
                    ((MainActivity) getActivity()).fragmentReplace(R.id.calendar, R.integer.addTrainingWindow);
                }
            }
        });
    }


}
