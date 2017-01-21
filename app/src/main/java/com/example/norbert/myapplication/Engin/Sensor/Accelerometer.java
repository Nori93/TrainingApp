package com.example.norbert.myapplication.Engin.Sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;

/**
 * Created by norbert on 21.01.2017.
 */

public class Accelerometer implements SensorEventListener{


    private float lastX, lastY, lastZ;
    private float deltaX=0,deltaY=0,deltaZ=0;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    public Accelerometer(SensorManager manager){
        sensorManager = manager;
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else
        {
            Log.e("Sensor : Accelerometer","No Manager");
        }

    }

    public void onResume(){
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause(){
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        deltaX = lastX - event.values[0];
        deltaY = lastY - event.values[1];
        deltaZ = lastZ - event.values[2];

        /*
        Log.e("Sensor : Accelerometer","X = " + deltaX);
        Log.e("Sensor : Accelerometer","Y = " + deltaY);
        Log.e("Sensor : Accelerometer","Z = " + deltaZ);
        */
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {  }


}
