package com.example.norbert.myapplication.Engin.Objects;

public class Exercise {

    private int ID;
    private String Nazwa;
    private String Opis;
    private String Instrukcje;
    private String Sciezka;


    public Exercise(int id, String nazwa, String opis, String instrukcje, String sciezka) {
        ID = id;
        Nazwa = nazwa;
        Opis = opis;
        Instrukcje = instrukcje;
        Sciezka = sciezka;
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

    public String getSciezka() {
        return Sciezka;
    }

    public void setSciezka(String sciezka) {
        Sciezka = sciezka;
    }

    public String getInstrukcje() {
        return Instrukcje;
    }

    public void setInstrukcje(String instrukcje) {
        Instrukcje = instrukcje;
    }
}
