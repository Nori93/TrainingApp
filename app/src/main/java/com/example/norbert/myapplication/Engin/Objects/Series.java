package com.example.norbert.myapplication.Engin.Objects;

import java.util.ArrayList;

/**
 * Created by Krzysztof Bzoma on 06.01.2017.
 */

public class Series {
    private float weights;
    private int
            id,
            repeats,
            id_tr,
            id_cw;

    public Series(){}
    public Series(int id, int repeats, float weights, int id_cw, int id_tr) {
        this.repeats = repeats;
        this.weights = weights;
        this.id_cw = id_cw;
        this.id = id;
        this.id_tr = id_tr;
    }
    public Series( int repeats, float weights, int id_cw, int id_tr) {
        this.repeats = repeats;
        this.weights = weights;
        this.id_cw = id_cw;
        this.id_tr = id_tr;
    }
    public Series( int repeats, float weights, int id_cw) {
        this.repeats = repeats;
        this.weights = weights;
        this.id_cw = id_cw;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public float getWeights() {
        return weights;
    }

    public void setWeights(float weights) {
        this.weights = weights;
    }

    public int getId_tr() {
        return id_tr;
    }

    public void setId_tr(int id_tr) {
        this.id_tr = id_tr;
    }

    public int getId_cw() {
        return id_cw;
    }

    public void setId_cw(int id_cw) {
        this.id_cw = id_cw;
    }
}
