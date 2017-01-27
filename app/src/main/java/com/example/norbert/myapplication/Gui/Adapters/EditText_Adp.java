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


    //Elements
    EditText input;
    TextView title;


    // List that containt all exercise
    String[] names  = {"ID","Name","Description","Instruction","Icon Path"};
    String[] data;

    //Dimension of icons in list





    public EditText_Adp(Context applicationContext){
        this.context = applicationContext;
        this.inflater = (LayoutInflater.from(applicationContext));

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

   /** @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.edit_text_adp, null);
        input = (EditText) view.findViewById(R.id.et_adp_input);
        title = (TextView)view.findViewById(R.id.et_adp_text);

        title.setText(names[position]);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                data[position] = title.getText().toString();
                Log.e("EditTextAdp","Add" + data[position]);
            }
        });

        return view;
    }*/

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

    public String[] getAllData(){return data;}
}
