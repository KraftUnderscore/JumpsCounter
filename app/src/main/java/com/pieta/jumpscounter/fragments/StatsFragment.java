package com.pieta.jumpscounter.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pieta.jumpscounter.R;

public class StatsFragment extends Fragment {

    private TextView totalJumps;
    private TextView totalSessions;
    private TextView totalTime;
    private TextView totalSpeed;

    private TextView bestJumps;
    private TextView bestTime;

    private Stats stats;

    public StatsFragment() {
        super(R.layout.fragment_stats);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        totalJumps = view.findViewById(R.id.stats_jumps_value);
        totalSessions = view.findViewById(R.id.stats_sessions_value);
        totalTime = view.findViewById(R.id.stats_time_value);
        totalSpeed = view.findViewById(R.id.stats_speed_value);

        bestJumps = view.findViewById(R.id.stats_best_jumps_value);
        bestTime = view.findViewById(R.id.stats_best_time_value);

        updateStatsText();
    }

    public void updateStatsData(Stats stats) {
        this.stats = stats;
    }

    private void updateStatsText() {
        if(stats == null) return;

        totalJumps.setText(stats.getTotalJumpsString());
        totalSessions.setText(stats.getTotalSessionsString());
        totalTime.setText(stats.getTotalTimeString());
        totalSpeed.setText(stats.getTotalSpeedString());

        bestJumps.setText(stats.getBestJumpsString());
        bestTime.setText(stats.getBestTimeString());
    }
}