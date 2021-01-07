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

    public String getJumpsString() {
        return String.format(Locale.getDefault(), "%d", jumps);
    }

    public String getDurationString() {
        return String.format(Locale.getDefault(), "%.1f sec", duration);
    }

    public String getDateString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        return format.format(timeStamp);
    }

    public String getAvgString() {
        return String.format(Locale.getDefault(), "%.1f jump/sec", jumps/duration);
    }
}
