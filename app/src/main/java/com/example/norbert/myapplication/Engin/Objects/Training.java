package com.example.norbert.myapplication.Engin.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norbert on 01.01.2017.
 */

public class Training {

    private String
            name,           //Name of Training
            desc,           //Short description what give as the full training
            author;          //Name of author of the training
    private int
            id,             // id in database
            type,           // type of training
            difficulty,     // difficulty of full training f.e. for beginners
            time_day,       // how much time suppose it take in a day
            time_sum;       // how long the training take f.e. 5 weeks or 3 months

    private ArrayList<Exercise> exerciseList;

    public Training(String name, String desc, String author,
                    int id, int type, int difficulty, int time_day, int time_sum) {
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.id = id;
        this.type = type;
        this.difficulty = difficulty;
        this.time_day = time_day;
        this.time_sum = time_sum;
        exerciseList = new ArrayList<Exercise>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getTime_day() {
        return time_day;
    }

    public void setTime_day(int time_day) {
        this.time_day = time_day;
    }

    public int getTime_sum() {
        return time_sum;
    }

    public void setTime_sum(int time_sum) {
        this.time_sum = time_sum;
    }

    public Exercise getExercise(int index){
        return exerciseList.get(index);
    }

    public int size(){
        return exerciseList.size();
    }

    public boolean add(Exercise e){
       return exerciseList.add(e);
    }

    public boolean remove(Exercise e){
       return exerciseList.remove(e);
    }

    public void clear(){
        exerciseList.clear();
    }


}
