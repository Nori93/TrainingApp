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
    String[] names;
    String[] data;

    //Dimension of icons in list





    public EditText_Adp(Context applicationContext,String[] names){
        this.context = applicationContext;
        this.inflater = (LayoutInflater.from(applicationContext));
        this.names = names;
        this.data = new String[names.length];

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

    @Override
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
    }

    public String[] getAllData(){return data;}
}
