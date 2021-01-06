package com.pieta.jumpscounter.logic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class LinearAccelerationDetector implements JumpDetector, SensorEventListener {
    private JumpCollector collector;
    private Sensor sensor;
    private boolean isDetecting = false;
    private float previousX, previousY, previousZ;
    private final float threshold = 0.05f;

    public LinearAccelerationDetector(Context context) {
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, sensor, manager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void setCollector(JumpCollector collector) {
        this.collector = collector;
    }

    @Override
    public void updateCollector() {
        collector.collectJump();
    }

    @Override
    public void startDetecting() {
        isDetecting = true;
    }

    @Override
    public void stopDetecting() {
        isDetecting = false;
    }

    private boolean isJump(float x, float y, float z) {
        return Math.abs(previousX - x) > threshold
               || Math.abs(previousY - y) > threshold
               || Math.abs(previousZ - z) > threshold;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(!isDetecting) return;

        float currentX = sensorEvent.values[0];
        float currentY = sensorEvent.values[1];
        float currentZ = sensorEvent.values[2];

        if(isJump(currentX, currentY, currentZ)) updateCollector();

        previousX = currentX;
        previousY = currentY;
        previousZ = currentZ;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
