package com.pieta.jumpscounter.fragments;

import com.pieta.jumpscounter.data.Session;

import java.util.List;
import java.util.Locale;

public class Stats {
    private int most_jumps;
    private int week_jumps;
    private float most_duration;
    private float week_duration;
    private float most_avg;
    private float week_avg;

    public void setMostJumps(int jumps) {
        most_jumps = jumps;
    }

    public void setMostDuration(float duration) {
        most_duration = duration;
    }

    public void setMostAvg(float avg) {
        most_avg = avg;
    }

    public void setWeekData(List<Session> sessionList) {
        week_duration = 0f;
        week_jumps = 0;
        for (int i = 0; i < sessionList.size(); i++) {
            week_jumps += sessionList.get(i).jumps;
            week_duration += sessionList.get(i).duration;
        }
        week_avg = week_jumps / week_duration;
    }

    public String getMostJumpsString() {
        return String.format(Locale.getDefault(), "%d", most_jumps);
    }

    public String getMostDurationString() {
        return String.format(Locale.getDefault(), "%.1f sec", most_duration);
    }

    public String getMostAvgString() {
        return String.format(Locale.getDefault(), "%.1f jump/sec", most_avg);
    }

    public String getWeekJumpString() {
        return String.format(Locale.getDefault(), "%d", week_jumps);
    }

    public String getWeekDurationString() {
        return String.format(Locale.getDefault(), "%f", (week_duration / 60f));
    }

    public String getWeekAvgString() {
        return String.format(Locale.getDefault(), "%.1f jump/sec", week_avg);
    }
}
