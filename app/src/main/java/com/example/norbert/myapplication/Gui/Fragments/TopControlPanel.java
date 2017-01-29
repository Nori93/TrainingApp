package com.example.norbert.myapplication.Gui.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.R;

public class TopControlPanel extends Fragment{

    private LinearLayout
            button1,
            button2,
            button3,
            button4;

    private TextView
            button1_text,
            button2_text,
            button3_text,
            button4_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topcontrol,container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       try{
        Gui(view);
       }catch (Exception e){
           Log.e("Error",e.getMessage().toString());
       }
    }

    private void Gui(View view){
        button1 = (LinearLayout)view.findViewById(R.id.tp_Button_1);
        button2 = (LinearLayout)view.findViewById(R.id.tp_Button_2);
      //  button3 = (LinearLayout)view.findViewById(R.id.tp_Button_3);
        button4 = (LinearLayout)view.findViewById(R.id.tp_Button_4);

        button1_text = (TextView) view.findViewById(R.id.tp_Button_1_text);
        button2_text = (TextView) view.findViewById(R.id.tp_Button_2_text);
      //  button3_text = (TextView) view.findViewById(R.id.tp_Button_3_text);
        button4_text = (TextView) view.findViewById(R.id.tp_Button_4_text);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickColor(1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickColor(2);
            }
        });

//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OnClickColor(3);
//            }
//        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickColor(4);
            }
        });
    }

    private void OnClickColor(int position){
        switch (position){
            case 1:
                button1.setBackgroundColor(getResources().getColor(R.color.colorTopControlOne));
                button2.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));
             //   button3.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));
                button4.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));

                button1_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOn));
                button2_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
               // button3_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
                button4_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
                break;
            case 2:
                button1.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));
                button2.setBackgroundColor(getResources().getColor(R.color.colorTopControlOne));
             //   button3.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));
                button4.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));

                button1_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
                button2_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOn));
               // button3_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
                button4_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
                break;
            case 3:
                button1.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));
                button2.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));
              //  button3.setBackgroundColor(getResources().getColor(R.color.colorTopControlOne));
                button4.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));

                button1_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
                button2_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
               // button3_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOn));
                button4_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
                break;
            case 4:
                button1.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));
                button2.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));
              //  button3.setBackgroundColor(getResources().getColor(R.color.colorTopControlTwo));
                button4.setBackgroundColor(getResources().getColor(R.color.colorTopControlOne));

                button1_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
                button2_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
               // button3_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOff));
                button4_text.setTextColor(getResources().getColor(R.color.colorTopControlTextOn));
                break;
        }
        ((MainActivity)getActivity()).changeScene(position);
    }

}
