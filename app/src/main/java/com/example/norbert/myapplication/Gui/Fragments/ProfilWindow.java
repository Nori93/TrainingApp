package com.example.norbert.myapplication.Gui.Fragments;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.norbert.myapplication.Engin.DataBaseHelper.DatabaseOperations;
import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Objects.UserInformation;
import com.example.norbert.myapplication.Engin.Repository.ExerciseRepository;
import com.example.norbert.myapplication.Engin.Repository.InformationRepository;
import com.example.norbert.myapplication.R;

public class ProfilWindow extends Fragment {


    Context ctx;
    ListView lista;
    TextView calories,fat,carb,protein;
    private ArrayAdapter<String> Listadapter;
    private ArrayList<String> arrayList;
    InformationRepository InformationRepository;
    String regexStr = "^[0-9]*$",sCarb="% Calories as carbohydrates:",sProteins="% Calories as proteinss:",sFat="% Calories as fat:";
    float tluszcz,bialko,weglowodany;
    float which1=1.2f;
    CharSequence[] activity={"low","medium","high"};
    CharSequence[] Target={"Reduce","Gain","Maintain actual weight"};
    String target="Reduce";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil_window,container, false);
        ctx= this.getActivity();
        InformationRepository = new InformationRepository();
        final DatabaseOperations DB = new DatabaseOperations(ctx);
        final UserInformation CR =  InformationRepository.getInformationData(DB); // Dostajesz cały wypełniony obiekt!
        final ArrayList<String> list = new ArrayList<String>();


        lista = (ListView) view.findViewById(R.id.ListView);
        calories = (TextView) view.findViewById(R.id.textView);
        carb = (TextView) view.findViewById(R.id.textView6);
        fat= (TextView) view.findViewById(R.id.textView5);
        protein = (TextView) view.findViewById(R.id.textView3);
        InformationRepository.putInformationData(DB,new UserInformation(CR.getWeight(),CR.getHeight(),2000,CR.getActivityLvl(),CR.getFat(),CR.getCarb(),CR.getProtein(),CR.getTarget()));
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
        list.add(sCarb+(Math.round(((CR.getCarb()*4)*100)/CR.getCal())));
        list.add(sFat+(Math.round(((CR.getFat()*9)*100)/CR.getCal())));
        list.add(sProteins+(Math.round(((CR.getProtein()*4)*100)/CR.getCal())));
        list.add("Target: "+CR.getTarget());

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,final int position, long arg3)
            {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
               final EditText text = new EditText(ctx);

                switch(position) {
                    case 0:
                        alertDialog.setView(text);
                        alertDialog.setTitle("Set your Weight (kg)");
                        break;
                    case 1:
                        alertDialog.setView(text);
                        alertDialog.setTitle("Set your Height(cm)");
                        break;
                    case 2:
                        alertDialog.setTitle("Set your Activity Level").setSingleChoiceItems(activity,0,new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if(which==0) {
                                which1 = 1.2f;
                            }
                            else if(which==1){
                                which1=1.4f;
                            }
                            else if(which==2){
                                which1=1.6f;
                            }

                             }
                     });
                        text.setText(""+which1);
                        regexStr="[+-]?([0-9]*[.])?[0-9]+";
                        break;
                    case 3:
                        alertDialog.setView(text);
                        alertDialog.setTitle("Set your carbs ");
                        break;
                    case 4:
                        alertDialog.setView(text);
                        alertDialog.setTitle("Set your fat ");
                        break;
                    case 5:
                        alertDialog.setView(text);
                        alertDialog.setTitle("Set your protein ");
                        break;
                    case 6:
                        alertDialog.setTitle("Set your Target").setSingleChoiceItems(Target,0,new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(which==0) {
                                    target = "Reduce";
                                }
                                else if(which==1){
                                    target="Gain";
                                }
                                else if(which==2){
                                    target="Maintain actual weight";
                                }

                            }
                        });
                        text.setText(""+target);
                        regexStr=".*";
                        break;

                }

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

                                    list.set(position, "Activity Level: " + which1);
                                    CR.setActivityLvl(Float.valueOf(text.getText().toString()));
                                    Listadapter.notifyDataSetChanged();
                                    break;
                                case 3:
                                    weglowodany=Float.valueOf(text.getText().toString());
                                    if((weglowodany+bialko+tluszcz)<=101){
                                    list.set(position, sCarb + text.getText()+"%");
                                    CR.setCarb((float) Math.ceil((CR.getCal()*((Float.valueOf(text.getText().toString()))/100))/4));
                                    carb.setText("Total carbohydrates needed: "+CR.getCarb()+"g");
                                    Listadapter.notifyDataSetChanged();
                                    break;}
                                    else{
                                        Toast.makeText(ctx,"Sum of calories, fat and protein needs to be 100%",Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                case 4:
                                    tluszcz=Float.valueOf(text.getText().toString());
                                    if((weglowodany+bialko+tluszcz)<=100){
                                    list.set(position, sFat + text.getText()+"%");
                                    CR.setFat((float) Math.ceil((CR.getCal()*((Float.valueOf(text.getText().toString()))/100))/9));
                                    fat.setText("Total fat needed: "+CR.getFat()+"g");
                                    Listadapter.notifyDataSetChanged();
                                        break;
                                    }
                                    else{
                                        Toast.makeText(ctx,"Sum of calories, fat and protein needs to be 100%",Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                case 5:
                                    bialko=Float.valueOf(text.getText().toString());
                                    if((weglowodany+bialko+tluszcz)<=100){
                                    list.set(position, sProteins + text.getText()+"%");
                                    CR.setProtein((float) Math.ceil((CR.getCal()*((Float.valueOf(text.getText().toString()))/100))/4));
                                    protein.setText("Total protein needed: "+CR.getProtein()+"g");
                                    Listadapter.notifyDataSetChanged();
                                    break;
                                    }
                                    else{
                                        Toast.makeText(ctx,"Sum of calories, fat and protein needs to be 100%",Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                case 6:
                                        list.set(position,"Target: "+target);
                                        CR.setTarget(text.getText().toString());
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
                InformationRepository.putInformationData(DB,new UserInformation(CR.getWeight(),CR.getHeight(),2000,CR.getActivityLvl(),CR.getFat(),CR.getCarb(),CR.getProtein(),CR.getTarget()));


                // Kamil Czaja tests

                ExerciseRepository Exerciserepo = new ExerciseRepository();
                ArrayList<Exercise> tmp =  Exerciserepo.getAllExercise(DB);
                Exercise tmp2 =  Exerciserepo.getExerciseById(1,DB);

                ///////////

            }
        });


        return view;
    }

    private void calcTotalCalories(){

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
