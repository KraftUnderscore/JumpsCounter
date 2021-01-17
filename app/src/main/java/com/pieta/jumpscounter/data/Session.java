package com.pieta.jumpscounter.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Entity
public class Session {
    @PrimaryKey
    @ColumnInfo(name = "timestamp")
    long timestamp;

    @ColumnInfo(name = "duration")
    public float duration;

    @ColumnInfo(name = "jumps")
    public int jumps;

    public Session() {
        this.timestamp = System.currentTimeMillis();
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
        return String.format(Locale.getDefault(), "%.1f min", duration/60);
    }

    public String getDateString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        return format.format(timestamp);
    }

    public String getAvgString() {
        return String.format(Locale.getDefault(), "%.1f jump/sec", jumps/duration);
    }
}
