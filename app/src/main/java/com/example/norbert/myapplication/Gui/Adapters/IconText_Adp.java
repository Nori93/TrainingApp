package com.example.norbert.myapplication.Gui.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Engin.Objects.Training;
import com.example.norbert.myapplication.R;

import java.util.List;
/**
 * Created by norbert on 31.12.2016.
 */

public class IconText_Adp extends BaseAdapter {


    //Init <some more>
    private Context context;
    private LayoutInflater inflater;

    //Elements
    ImageView  icon;
    TextView title;


    // List that containt all exercise ,training
    List<Exercise> exercisesList;
    List<Training> trainingList;

    // true = exercise ;false traning
    boolean type = true;
    //Dimension of icons in list
    private int widthIcon = 30;
    private int heightIcon = 30;
    private Bitmap temp_icon;



    public IconText_Adp(Context applicationContext, List<Training> trainingList,boolean t){
        this.context = applicationContext;
        this.inflater = (LayoutInflater.from(applicationContext));
        this.trainingList = trainingList;
        this.type = t;
    }

    public IconText_Adp(Context applicationContext, List<Exercise> exercisesList){
        this.context = applicationContext;
        this.inflater = (LayoutInflater.from(applicationContext));
        this.exercisesList = exercisesList;
    }


    @Override
    public int getCount() {
        if(type)
            return exercisesList.size();
        else
            return trainingList.size();
    }

    @Override
    public Exercise getItem(int position) {
       return exercisesList.get(position);
    }

    public Training getItemT(int position) { return trainingList.get(position); }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.icon_text_adp, null);
        icon = (ImageView)view.findViewById(R.id.it_adp_icon);
        title = (TextView)view.findViewById(R.id.it_adp_text);

        icon.setMaxWidth(widthIcon);
        icon.setMaxHeight(heightIcon);

        try {
           // temp_icon = BitmapFactory.decodeFile(exercisesList.get(position).getSciezka());
           // icon.setImageBitmap(temp_icon);

        }catch (Exception e){
            Log.e("Error",position + " do not set on IconText adp ");
        }
        if(type)
            title.setText(exercisesList.get(position).getNazwa());
        else
            title.setText(trainingList.get(position).getNazwa());

        return view;
    }
}
