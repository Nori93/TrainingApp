package com.example.norbert.myapplication.Gui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.norbert.myapplication.R;

public class HoriIconList_Adp extends BaseAdapter {

    Context context;
    LayoutInflater inflter;
    int table[];
    int i = 0;
    int num;


    public HoriIconList_Adp(Context applicationContext, int size,int numb) {
        this.context = applicationContext;
        this.table = new  int[size];
        this.num = numb;
        inflter = (LayoutInflater.from(applicationContext));
      //  scroll();
    }

    @Override
    public int getCount() {
        return table.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.horiiconlist_adp, null);
        ImageView icon = (ImageView) view.findViewById(R.id.hil_icon);
        icon.setImageResource(table[i]);
        return view;
    }


//    private void scroll() {
//        for (int i = 0; i < this.table.length; i++) {
//            if( i <= num){
//          //  this.table[i] = R.drawable.adp_full;}
//            else {
//          //  this.table[i] = R.drawable.adp_empty;}
//
//        }
//    }

}

