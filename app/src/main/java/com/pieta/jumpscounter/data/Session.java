package com.pieta.jumpscounter.data;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Session {
    long timeStamp;
    float duration;
    int jumps;

    public Session() {
        this.timeStamp = System.currentTimeMillis();
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @SuppressLint("DefaultLocale")
    public String getJumpsString() {
        return String.format("%d", jumps);
    }

    @SuppressLint("DefaultLocale")
    public String getDurationString() {
        return String.format("%.1f sec", duration);
    }

    @SuppressLint("DefaultLocale")
    public String getDateString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        return format.format(timeStamp);
    }

    @SuppressLint("DefaultLocale")
    public String getAvgString() {
        return String.format("%.1f jump/sec", jumps/duration);
    }
}
