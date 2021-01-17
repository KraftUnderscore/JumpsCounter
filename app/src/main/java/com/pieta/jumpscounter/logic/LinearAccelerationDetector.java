package com.pieta.jumpscounter.logic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class LinearAccelerationDetector implements JumpDetector, SensorEventListener {

    private class Vector3 {
        public float x;
        public float y;
        public float z;

        public Vector3(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public float magnitude() {
            return (float) Math.sqrt(x * x + y * y + z * z);
        }

        public boolean lengthCheck() {
            final float mult = 15f;
            return (x > y * mult && x > z * mult) ||
                   (y > x * mult && y > z * mult) ||
                   (z > y * mult && z > x * mult);
        }
    }

    private JumpCollector collector;
    private boolean isDetecting = false;
    private Vector3 previousVec;

    public LinearAccelerationDetector(Context context) {
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
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
        previousVec = new Vector3(0, 0, 0);
    }

    @Override
    public void stopDetecting() {
        isDetecting = false;
    }

    private boolean isJump(Vector3 currentVec) {
        float dif = Math.abs(previousVec.magnitude() - currentVec.magnitude());
        float minThreshold = 1.8f;
        float maxThreshold = 2.3f;
        return dif > minThreshold && dif < maxThreshold && currentVec.lengthCheck();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(!isDetecting) return;

        Vector3 currentVec = new Vector3(sensorEvent.values[0], sensorEvent.values[1],
                sensorEvent.values[2]);

        if(isJump(currentVec)) updateCollector();
        previousVec = currentVec;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
