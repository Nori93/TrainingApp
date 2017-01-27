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
    private String opis;
    private String nazwa;
    private List<Series> Serie;


    public Training(Integer id, String data, List<Series> serie,String nazwa, String opis) {
        ID = id;
        Data = data;
        Serie = serie;
        this.opis = opis;
        this.nazwa = nazwa;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
