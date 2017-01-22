package com.example.norbert.myapplication.Engin.Objects;

/**
 * Created by Kamil on 2017-01-03.
 */

public class UserInformation {

    private float weight;
    private float height;
    private float cal;
    private float activityLvl;
    private float fat;
    private float carb;
    private float protein;
    private String target;
    private String bodyType;
    private int sex;
    private int age;



    public UserInformation(){}

    public UserInformation(float weight, float height, float cal, float activityLvl, float fat, float carb, float protein, String target, String bodyType, int sex,int age)
    {
        this.weight = weight;
        this.height = height;
        this.cal = cal;
        this.activityLvl = activityLvl;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
        this.target = target;
        this.age = age;
    }


    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getCal() {
        return cal;
    }

    public void setCal(float cal) {
        this.cal = cal;
    }

    public float getActivityLvl() {
        return activityLvl;
    }

    public void setActivityLvl(float activityLvl) {
        this.activityLvl = activityLvl;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCarb() {
        return carb;
    }

    public void setCarb(float carb) {
        this.carb = carb;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

