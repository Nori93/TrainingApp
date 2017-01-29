package com.example.norbert.myapplication.Gui.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.norbert.myapplication.Engin.Objects.Exercise;
import com.example.norbert.myapplication.Gui.Fragments.InputWindow;
import com.example.norbert.myapplication.R;

import java.util.List;
import java.util.Objects;

/**
 * Created by norbert on 31.12.2016.
 */

public class EditText_Adp extends BaseAdapter {


    //Init <some more>
    private Context context;
    private LayoutInflater inflater;





    // List that containt all exercise
    String[] names  ;//= {"ID","Name","Description","Instruction","Icon Path"};
    String[] data;

    //Dimension of icons in list





    public EditText_Adp(Context applicationContext,int tag){
        this.context = applicationContext;
        this.inflater = (LayoutInflater.from(applicationContext));
        setInput(tag);
        this.data = new String[this.names.length];

    }


    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Objects getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        //ViewHolder holder = null;
        final ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.edit_text_adp, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.et_adp_text);
            holder.editText1 = (EditText) convertView.findViewById(R.id.et_adp_input);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.ref = position;

        holder.textView1.setText(names[position]);
        holder.editText1.setText(data[position]);
        holder.editText1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                data[holder.ref] = arg0.toString();
                Log.e("Typing",arg0.toString());
            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView textView1;
        EditText editText1;
        int ref;
    }
    public void setInput(int tag){
        switch (tag) {
            case 51:
                names = new String[] {"Name","Description","Instruction","Icon Path"};
                break;
            case 52:
                names= new String[] {"Repeats", "Weights", "ID Exercise", "ID Training"};
                break;
            case 53:
                names = new String[] {"Name","Description"};
                break;
            case 54:
                names =new String[] {"Weight","Height", "Calories", "Activity","Fat", "Carb", "Protein", "Target","Body Type", "Sex","Age"};
                break;
        }
    }

    public String[] getAllData(){return data;}
}
