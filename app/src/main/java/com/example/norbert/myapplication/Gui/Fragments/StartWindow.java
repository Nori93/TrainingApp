package com.example.norbert.myapplication.Gui.Fragments;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.MainActivity;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Objects.UserInformation;
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
import com.example.norbert.myapplication.Engin.Repository.InformationRepository;
import com.example.norbert.myapplication.Engin.Repository.TrainingRepository;
import com.example.norbert.myapplication.R;

public class StartWindow extends Fragment {


    Context ctx;
    ListView lista;
    TextView calories,fat,carb,protein;
    private ArrayAdapter<String> Listadapter;
    private ArrayList<String> arrayList;
    InformationRepository InformationRepository;
    String regexStr = "^[0-9]*$";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start_window,container, false);
        ctx= this.getActivity();
        InformationRepository = new InformationRepository();
        final DatabaseOperations DB = new DatabaseOperations(ctx);
        final UserInformation CR =  InformationRepository.getInformationData(DB); // Dostajesz cały wypełniony obiekt!
        final ArrayList<String> list = new ArrayList<String>();
        final CharSequence[] activity={"low","medium","high"};

        lista = (ListView) view.findViewById(R.id.ListView);
        calories = (TextView) view.findViewById(R.id.textView);
        carb = (TextView) view.findViewById(R.id.textView6);
        fat= (TextView) view.findViewById(R.id.textView5);
        protein = (TextView) view.findViewById(R.id.textView3);
        InformationRepository.putInformationData(DB,new UserInformation(CR.getWeight(),CR.getHeight(),2000,CR.getActivityLvl(),CR.getFat(),CR.getCarb(),CR.getProtein(),"cel"));
        calories.setText("Total Calories Needed: "+CR.getCal());
        carb.setText("Total carbohydrates needed: "+CR.getCarb());
        fat.setText("Total fat needed: "+CR.getFat());
        protein.setText("Total protein needed: "+CR.getProtein());
        Listadapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.customlayout, list);
        lista.setAdapter(Listadapter);
        Listadapter.notifyDataSetChanged();
        list.add("Weight: "+CR.getWeight()+"kg");
        list.add("Height: "+CR.getHeight()+"cm");
        list.add("Activity level: "+CR.getActivityLvl());
        list.add("Set your % of calories as carbs:");
        list.add("Set your % of calories as fat:");
        list.add("Set your % of calories as proteins:");

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,final int position, long arg3)
            {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);

                final EditText text = new EditText(ctx);
                switch(position) {
                    case 0:
                        alertDialog.setTitle("Set your Weight (kg)");
                        break;
                    case 1:
                        alertDialog.setTitle("Set your Height(cm)");
                        break;
                    case 2:
                        //alertDialog.setTitle("Set your Activity Level").setSingleChoiceItems(activity,0,)
                        regexStr="[+-]?([0-9]*[.])?[0-9]+";
                        break;
                    case 3:
                        alertDialog.setTitle("Set your carbs ");
                        break;
                    case 4:
                        alertDialog.setTitle("Set your fat ");
                        break;
                    case 5:
                        alertDialog.setTitle("Set your protein ");
                        break;
                }

                alertDialog.setView(text);
                alertDialog.setNeutralButton("OK",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {
                        // Write your code here to execute after dialog

                        if (text.getText().toString().trim().matches(regexStr)&&text.getText().toString().isEmpty()==false) {
                            switch (position) {
                                case 0:
                                    list.set(position, "Weight: " + text.getText()+"kg");
                                    CR.setWeight(Float.valueOf(text.getText().toString()));
                                    Listadapter.notifyDataSetChanged();
                                    break;
                                case 1:
                                    list.set(position, "Height: " + text.getText()+"cm");
                                    CR.setHeight(Float.valueOf(text.getText().toString()));
                                    Listadapter.notifyDataSetChanged();
                                    break;
                                case 2:
                                    list.set(position, "Activity Level: " + text.getText());
                                    CR.setActivityLvl(Float.valueOf(text.getText().toString()));
                                    Listadapter.notifyDataSetChanged();
                                    break;
                                case 3:
                                    list.set(position, "Carbs: " + text.getText()+"%");
                                    CR.setCarb((float) Math.ceil((CR.getCal()*((Float.valueOf(text.getText().toString()))/100))/4));
                                    carb.setText("Total carbohydrates needed: "+CR.getCarb()+"g");
                                    Listadapter.notifyDataSetChanged();
                                    break;
                                case 4:
                                    list.set(position, "Fat: " + text.getText()+"%");
                                    CR.setFat((float) Math.ceil((CR.getCal()*((Float.valueOf(text.getText().toString()))/100))/9));
                                    fat.setText("Total fat needed: "+CR.getFat()+"g");
                                    Listadapter.notifyDataSetChanged();
                                    break;
                                case 5:

                                    list.set(position, "Protein: " + text.getText()+"%");
                                    CR.setProtein((float) Math.ceil((CR.getCal()*((Float.valueOf(text.getText().toString()))/100))/4));
                                    protein.setText("Total protein needed: "+CR.getProtein()+"g");
                                    Listadapter.notifyDataSetChanged();
                                    break;
                            }
                        }
                        else{
                            Toast.makeText(ctx,"Wrong values",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                final AlertDialog alert=alertDialog.create();
                alert.show();
                InformationRepository.putInformationData(DB,new UserInformation(CR.getWeight(),CR.getHeight(),2000,CR.getActivityLvl(),CR.getFat(),CR.getCarb(),CR.getProtein(),"cel"));


                // Kamil Czaja tests

                ExerciseRepository Exerciserepo = new ExerciseRepository();
                ArrayList<Exercise> tmp =  Exerciserepo.getAllExercise(DB);
                Exercise tmp2 =  Exerciserepo.getExerciseById(1,DB);

                TrainingRepository TrainingRepo = new TrainingRepository();


                ///////////
            }
        });
       // buttonTest.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {


              //  InformationRepository.putInformationData(DB,new UserInformation(1,2,3,4,5,6,7)); // Przekazujesz cały wypełniony obiekt!

      //      }
      //  });

      //  buttonGet = (Button) view.findViewById(R.id.button3);

     //   buttonGet.setOnClickListener(new View.OnClickListener() {
     //       @Override
     //       public void onClick(View v) {
              //  DatabaseOperations DB = new DatabaseOperations(ctx);
             //   UserInformation CR =  InformationRepository.getInformationData(DB); // Dostajesz cały wypełniony obiekt!
             //   list.clear();
        //     list.set(0,"Weight:10");
         //   list.addItems("dsa"+CR.getFat());
        //       Test1.setText(list.get(0));
         //       Listadapter.notifyDataSetChanged();
        //    }
      //  });





        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
