package com.example.norbert.myapplication.Engin.Objects;

public class Exercise {

    private String
            name,                    //Name of the exercise
            desc,                    //Short description what give this exercise to as
            instruction;             //How to do this exercise


    private int
            id,                     // id from the database
            type,                   // type of exercise
            repeat,                 // numbers of repeat
            series,                 // numbers of series
            gap;                    // break between series

    public Exercise(){
        name = "";
        desc = "";
        instruction = "";
        id = 0;
        type = 0;
        repeat = 0;
        series = 0;
        gap = 0;
    }

    public Exercise( String name, String desc, String instruction,
                     int id, int type, int repeat, int series,int gap) {
        this.name = name;
        this.desc = desc;
        this.instruction = instruction;
        this.id = id;
        this.type = type;
        this.repeat = repeat;
        this.series = series;
        this.gap = gap;
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
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

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }
}
