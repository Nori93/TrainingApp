package com.example.norbert.myapplication.Engin.Sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by norbert on 21.01.2017.
 */

public class LinearAccelerometer implements SensorEventListener{


    private float lastX, lastY, lastZ;
    private float deltaX=0,deltaY=0,deltaZ=0;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    public LinearAccelerometer(SensorManager manager){
        sensorManager = manager;
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null){
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else
        {
            Log.e("Sensor : Linear","No Manager");
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


        Log.e("Sensor : Linear","X = " + deltaX);
        Log.e("Sensor : Linear","Y = " + deltaY);
        Log.e("Sensor : Linear","Z = " + deltaZ);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {  }


}
