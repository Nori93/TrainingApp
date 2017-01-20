package com.example.norbert.myapplication.Engin.Objects;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil Czaja on 20.01.2017.
 */

public class Training {
    private Integer ID;
    private  String Data;
    private List<Series> Serie;


    public Training(Integer id, String data, List<Series> serie) {
        ID = id;
        Data = data;
        Serie = serie;
    }

    public List<Series> getSerie() {
        return Serie;
    }

    public void setSerie(List<Series> serie) {
        Serie = serie;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
