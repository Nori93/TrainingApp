package com.example.norbert.myapplication.Engin.Objects;

public class Exercise {

    private int ID;
    private String Nazwa;
    private String Opis;


    public Exercise(int id, String nazwa, String opis) {
        ID = id;
        Nazwa = nazwa;
        Opis = opis;
    }

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
