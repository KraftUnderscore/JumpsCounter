package com.pieta.jumpscounter.fragments;

import android.content.Context;

import com.pieta.jumpscounter.R;
import com.pieta.jumpscounter.data.Session;

import java.util.Locale;

public class Stats {

    private int totalJumps;
    private int totalSessions;
    private float totalDuration;
    private float totalSpeed;

    private int bestJumps;
    private float bestTime;

    private final Context context;

    public Stats(Context context){
        super();
        this.context = context;
    }

    public void setTotalJumps(int totalJumps) {
        this.totalJumps = totalJumps;
    }

    public void setTotalSessions(int totalSessions) {
        this.totalSessions = totalSessions;
    }

    public void setTotalDuration(float totalDuration) {
        this.totalDuration = totalDuration;
    }

    public void setTotalSpeed(float totalSpeed) {
        this.totalSpeed = totalSpeed;
    }

    public void setBestSession(Session session) {
        bestJumps = session.jumps;
        bestTime = session.duration;
    }

    public String getTotalJumpsString() {
        return String.format(Locale.getDefault(), context.getResources().getString(R.string.jumps_value), totalJumps);
    }

    public String getTotalSessionsString() {
        return String.format(Locale.getDefault(), context.getResources().getString(R.string.session_value), totalSessions);
    }

    public String getTotalTimeString() {
        return formatTime(totalDuration);
    }

    public String getTotalSpeedString() {
        return String.format(Locale.getDefault(), context.getResources().getString(R.string.speed_value), totalSpeed);
    }

    public String getBestJumpsString() {
        return String.format(Locale.getDefault(), context.getResources().getString(R.string.jumps_value), bestJumps);
    }

    public String getBestTimeString() {
        return formatTime(bestTime);
    }

    private String formatTime(float duration) {
        if (duration < 60) {
            return String.format(Locale.getDefault(), context.getResources().getString(R.string.time_ss_value), duration);
        } else if (duration < 3600) {
            int min = (int) (duration / 60);
            return String.format(Locale.getDefault(), context.getResources().getString(R.string.time_mm_ss_value), min, duration%60);
        } else if (duration < 86400) {
            int hr = (int) (duration / 3600);
            return String.format(Locale.getDefault(), context.getResources().getString(R.string.time_hh_mm_value), hr, (int) (duration%3600 / 60));
        } else {
            int days = (int) (duration / 86400);
            return String.format(Locale.getDefault(), context.getResources().getString(R.string.time_dd_hh_value), days, duration%86400 / 3600);
        }
    }
}
