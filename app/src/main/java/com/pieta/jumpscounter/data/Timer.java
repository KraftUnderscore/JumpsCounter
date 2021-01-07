package com.pieta.jumpscounter.data;

public class Timer {
    float duration;
    long startTime;

    public Timer() {
        duration = 0;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        duration += System.currentTimeMillis() - startTime;
    }

    public void reset() {
        duration = 0;
    }

    public float getDuration() {
        return duration/1000f;
    }
}
