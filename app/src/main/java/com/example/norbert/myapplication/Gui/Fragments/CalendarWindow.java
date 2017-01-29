package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarWindow extends Fragment {
    CalendarView calendar;
    String selectedDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_calendar,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeCalendar(view);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        selectedDate = sdf.format(new Date(calendar.getDate()));
        selectedDate=selectedDate.substring(3,5);
        ((MainActivity)getActivity()).loadTrainings(selectedDate);

        //Toast.makeText(getActivity().getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();

    }

    public void initializeCalendar(View view) {

        calendar = (CalendarView)view.findViewById(R.id.calendar);



        // sets whether to show the week number.

        calendar.setShowWeekNumber(false);



        // sets the first day of week according to Calendar.

        // here we set Monday as the first day of the Calendar

        calendar.setFirstDayOfWeek(2);



        //The background color for the selected week.

        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));



        //sets the color for the dates of an unfocused month.

        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));



        //sets the color for the separator line between weeks.

        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));



        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.

        calendar.setSelectedDateVerticalBar(R.color.darkgreen);



        //sets the listener to be notified upon selected date change.

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            //show the selected date as a toast

            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                String month1=repairData(String.valueOf(month+1));
                String year1=String.valueOf(year);
                String day1=String.valueOf(day);
                ((MainActivity)getActivity()).setData(year1,month1,day1);
                if(selectedDate!=month1) {
                    ((MainActivity) getActivity()).loadTrainings(String.valueOf(month));
                    selectedDate=month1;

                }
                ((MainActivity)getActivity()).fragmentReplace(R.id.calendar,R.integer.trainingListWindow);


                Toast.makeText(getActivity().getApplicationContext(), day + "/" + month1 + "/" + year, Toast.LENGTH_LONG).show();

            }

        });


    }
    public String repairData(String month)
    {
        if(month.length()==1)
            month="0"+month;
        return month;
    }

}
