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
import android.widget.Button;
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
    Button Save,CountCalories;
    private ArrayAdapter<String> Listadapter;
    String regexStr = "^[0-9]*$",sCarb="% Calories as carbohydrates:",sProteins="% Calories as proteins:",sFat="% Calories as fat:";
    float tluszcz,bialko,weglowodany,which1;
    int sex;
    CharSequence[] activity={"low","medium","high"};
    CharSequence[] Target={"Reduce","Gain","Maintain actual weight"};
    CharSequence[] BodyType={"Mesomorph","Ectomorph","Endomorph"};
    CharSequence[] Sex={"Male","Female"};
    String target,bodyType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil_window,container, false);
        ctx= this.getActivity();
        final InformationRepository InformationRepository = new InformationRepository();
        final DatabaseOperations DB = new DatabaseOperations(ctx);
        final UserInformation CR =  InformationRepository.getInformationData(DB); // Dostajesz cały wypełniony obiekt!
        final ArrayList<String> list = new ArrayList<String>();

        CountCalories = (Button) view.findViewById(R.id.CountCalories);
        Save = (Button) view.findViewById(R.id.Save);
        lista = (ListView) view.findViewById(R.id.ListView);
        calories = (TextView) view.findViewById(R.id.textView);
        carb = (TextView) view.findViewById(R.id.textView6);
        fat= (TextView) view.findViewById(R.id.textView5);
        protein = (TextView) view.findViewById(R.id.textView3);

        Listadapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.customlayout, list);
        lista.setAdapter(Listadapter);
        Listadapter.notifyDataSetChanged();

        StartSetting(list,CR);
        ListHandling(list,CR,DB);
        calcTotalCalories(CR);

        Save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            SaveToBase(CR,DB,InformationRepository);
            }
        });
        CountCalories.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            CountCaloriesIf(CR);
            }
        });
        return view;
    }


private void CountCaloriesIf(UserInformation CR){

    if(CR.getWeight()==0){
        Toast.makeText(ctx,"You need to fill Weight",Toast.LENGTH_LONG).show();
    }
    else if(CR.getHeight()==0){
        Toast.makeText(ctx,"You need to fill Height",Toast.LENGTH_LONG).show();
    }
    else if(CR.getActivityLvl()==0){
        Toast.makeText(ctx,"You need to fill Activity level",Toast.LENGTH_LONG).show();
    }
    else if(CR.getTarget()==null){
        Toast.makeText(ctx,"You need to fill Target",Toast.LENGTH_LONG).show();
    }
    else if(CR.getBodyType()==null){
        Toast.makeText(ctx,"You need to fill Body Type",Toast.LENGTH_LONG).show();
    }
    else if(CR.getSex()==0){
        Toast.makeText(ctx,"You need to fill Sex",Toast.LENGTH_LONG).show();
    }
    else if(   CR.getAge()==0){
        Toast.makeText(ctx,"You need to fill Age",Toast.LENGTH_LONG).show();
    }
    else{
        CR.setCal((float)calcTotalCalories(CR));
        calories.setText("Total Calories Needed: "+Math.round(calcTotalCalories(CR)));
        Calories(CR);
    }
}
    private void StartSetting(ArrayList<String> list, UserInformation CR){
        calories.setText("Total Calories Needed: "+Math.round(CR.getCal()));
        carb.setText("Total carbohydrates needed: "+CR.getCarb()+"g");
        fat.setText("Total fat needed: "+CR.getFat()+"g");
        protein.setText("Total protein needed: "+CR.getProtein()+"g");
        weglowodany=(Math.round(((CR.getCarb()*4)*100)/CR.getCal()));
        tluszcz=(Math.round(((CR.getFat()*9)*100)/CR.getCal()));
        bialko=(Math.round(((CR.getProtein()*4)*100)/CR.getCal()));
        list.add("Weight: "+CR.getWeight()+"kg");
        list.add("Height: "+CR.getHeight()+"cm");
        list.add("Activity level: "+CR.getActivityLvl());
        list.add(sCarb+weglowodany+"%");
        list.add(sFat+tluszcz+"%");
        list.add(sProteins+bialko+"%");
        if(CR.getTarget()==null){
            list.add("Target: ");
        }
        else{
            list.add("Target: "+CR.getTarget());
        }
        if(CR.getBodyType()==null){
            list.add("Body Type: ");
        }
        else{
            list.add("Body Type: "+CR.getBodyType());
        }
        if(CR.getSex()==1) {
            list.add("Sex: Male");
        }
        else if(CR.getSex()==2){
            list.add("Sex: Female");
        }
        else{
            list.add("Sex: ");
        }
        list.add("Age: "+CR.getAge());
    }

    private void ListHandling(final ArrayList<String> list, final UserInformation CR,final DatabaseOperations DB){
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
                        which1=1.2f;
                        text.setText(""+which1);
                        alertDialog.setTitle("Set your Activity Level").setSingleChoiceItems(activity,0,new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(which==0) {
                                     which1 = 1.2f;
                                    text.setText(""+which1);
                                }
                                else if(which==1){
                                     which1=1.4f;
                                    text.setText(""+which1);
                                }
                                else if(which==2){
                                    which1=1.6f;
                                    text.setText(""+which1);
                                }
                            }

                        });


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
                        target = "Reduce";
                        text.setText(""+target);
                        alertDialog.setTitle("Set your Target").setSingleChoiceItems(Target,0,new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(which==0) {
                                    target = "Reduce";
                                    text.setText(""+target);
                                }
                                else if(which==1){
                                    target="Gain";
                                    text.setText(""+target);
                                }
                                else if(which==2){
                                    target="Maintain actual weight";
                                    text.setText(""+target);
                                }
                            }
                        });

                        regexStr=".*";
                        break;
                    case 7:
                        bodyType = "Mesomorph";
                        text.setText(""+bodyType);
                        alertDialog.setTitle("Set your Body Type").setSingleChoiceItems(BodyType,0,new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(which==0) {
                                   bodyType = "Mesomorph";
                                    text.setText(""+bodyType);
                                }
                                else if(which==1){
                                    bodyType="Ectomorph";
                                    text.setText(""+bodyType);
                                }
                                else if(which==2){
                                    bodyType="Endomorph";
                                    text.setText(""+bodyType);
                                }

                            }
                        });
                        regexStr=".*";
                        break;
                    case 8:
                        sex=1;
                        text.setText(""+sex);
                        alertDialog.setTitle("Set your sex").setSingleChoiceItems(Sex,0,new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(which==0) {
                                    sex = 1;
                                    text.setText(""+sex);
                                }
                                else if(which==1){
                                    sex=2;
                                    text.setText(""+sex);
                                }
                            }
                        });

                        regexStr=".*";
                        break;
                    case 9:
                        alertDialog.setView(text);
                        alertDialog.setTitle("Set your Age ");
                        break;

                }

                alertDialog.setNeutralButton("OK",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which) {
                        if (text.getText().toString().trim().matches(regexStr)&&text.getText().toString().isEmpty()==false) {
                           switchForOk(position,CR,text,list);
                        }
                        else{
                            Toast.makeText(ctx,"Wrong values",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                final AlertDialog alert=alertDialog.create();
                alert.show();
                // Kamil Czaja tests

                ExerciseRepository Exerciserepo = new ExerciseRepository();
                ArrayList<Exercise> tmp =  Exerciserepo.getAllExercise(DB);
                Exercise tmp2 =  Exerciserepo.getExerciseById(1,DB);

                ///////////
            }
        });

    }
    private void Calories(UserInformation CR){
        CR.setCarb((float) Math.ceil((CR.getCal()*((Float.valueOf(weglowodany))/100))/4));
        carb.setText("Total carbohydrates needed: "+CR.getCarb()+"g");
        CR.setFat((float) Math.ceil((CR.getCal()*((Float.valueOf(tluszcz))/100))/9));
        fat.setText("Total fat needed: "+CR.getFat()+"g");
        CR.setProtein((float) Math.ceil((CR.getCal()*((Float.valueOf(bialko))/100))/4));
        protein.setText("Total protein needed: "+CR.getProtein()+"g");
    }
    private void switchForOk(int position,UserInformation CR,EditText text,ArrayList<String> list){
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
                weglowodany=Float.valueOf(text.getText().toString());
                if((weglowodany+bialko+tluszcz)<=100){
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
            case 7:
                list.set(position,"Body Type: "+bodyType);
                CR.setBodyType(text.getText().toString());
                Listadapter.notifyDataSetChanged();
                break;
            case 8:
                list.set(position,"Sex: "+sex);
                CR.setSex(sex);
                if(CR.getSex()==1) {
                    list.set(8,"Sex: Male");
                }
                else{
                    list.set(8,"Sex: Female");
                }

                Listadapter.notifyDataSetChanged();
                break;
            case 9:
                list.set(position, "Age: " + text.getText());
                CR.setAge(Integer.valueOf(text.getText().toString()));
                Listadapter.notifyDataSetChanged();
                break;

        }
    }

    private double calcTotalCalories(UserInformation CR){
        double totalCalories = 0;
        if(CR.getSex()==1){
            totalCalories=(66.5+(13.7*CR.getWeight())+(5*CR.getHeight())-(6.8-CR.getAge()))*CR.getActivityLvl();
            switch (CR.getTarget()) {
                case "Gain":
                    switch (CR.getBodyType()) {
                        case "Mesomorph":
                            totalCalories = totalCalories + (0.15 * totalCalories);
                            break;
                        case "Endomorph":
                            totalCalories = totalCalories + (0.1 * totalCalories);
                            break;
                        default:
                            totalCalories = totalCalories + (0.2 * totalCalories);
                            break;
                    }
                    break;
                case "Reduce":
                    switch (CR.getBodyType()) {
                        case "Mesomorph":
                            totalCalories = totalCalories - (0.15 * totalCalories);
                            break;
                        case "Endomorph":
                            totalCalories = totalCalories - (0.2 * totalCalories);
                            break;
                        default:
                            totalCalories = totalCalories - (0.1 * totalCalories);
                            break;
                    }
                    break;
                default:
                    switch (CR.getBodyType()) {
                        case "Mesomorph":
                            totalCalories = totalCalories - 100;
                            break;
                        case "Endomorph":
                            totalCalories = totalCalories - 200;
                            break;
                        default:
                            totalCalories = totalCalories+0;
                            break;
                    }
                    break;
            }

        }
        else if(CR.getSex()==2){
        totalCalories=(65.5+(9.6*CR.getWeight())+(1.85*CR.getHeight())-(4.7-CR.getAge()))*CR.getActivityLvl();
            switch (CR.getTarget()) {
                case "Gain":
                    switch (CR.getBodyType()) {
                        case "Mesomorph":
                            totalCalories = totalCalories + (0.15 * totalCalories);
                            break;
                        case "Endomorph":
                            totalCalories = totalCalories + (0.1 * totalCalories);
                            break;
                        default:
                            totalCalories = totalCalories + (0.2 * totalCalories);
                            break;
                    }
                    break;
                case "Reduce":
                    switch (CR.getBodyType()) {
                        case "Mesomorph":
                            totalCalories = totalCalories - (0.15 * totalCalories);
                            break;
                        case "Endomorph":
                            totalCalories = totalCalories - (0.2 * totalCalories);
                            break;
                        default:
                            totalCalories = totalCalories - (0.1 * totalCalories);
                            break;
                    }
                    break;
                default:
                    switch (CR.getBodyType()) {
                        case "Mesomorph":
                            totalCalories = totalCalories - 100;
                            break;
                        case "Endomorph":
                            totalCalories = totalCalories - 200;
                            break;
                        default:
                            totalCalories = totalCalories+0;
                            break;
                    }
                    break;
            }

        }

        return totalCalories;
    }

    private void SaveToBase(UserInformation CR,DatabaseOperations DB,InformationRepository InformationRepository){

        InformationRepository.putInformationData(DB,new UserInformation(CR.getWeight(),CR.getHeight(),CR.getCal(),CR.getActivityLvl(),CR.getFat(),CR.getCarb(),CR.getProtein(),CR.getTarget(),CR.getBodyType(),CR.getSex(),CR.getAge()));

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
