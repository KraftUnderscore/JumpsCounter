package com.pieta.jumpscounter.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pieta.jumpscounter.R;

public class StatsFragment extends Fragment {

    private TextView mostJumps;
    private TextView longestSession;
    private TextView fastestJumping;

    private TextView weekDuration;
    private TextView weekJumps;
    private TextView weekAvg;

    private Stats stats;

    public StatsFragment() {
        super(R.layout.fragment_stats);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mostJumps = view.findViewById(R.id.stats_most_jumps);
        longestSession = view.findViewById(R.id.stats_most_duration);
        fastestJumping = view.findViewById(R.id.stats_most_avg);
        weekDuration = view.findViewById(R.id.stats_week_duration);
        weekJumps = view.findViewById(R.id.stats_week_jumps);
        weekAvg = view.findViewById(R.id.stats_week_avg);

        updateStatsText();
    }

    public void updateStatsData(Stats stats) {
        this.stats = stats;
    }

    private void updateStatsText() {
        if(stats == null) return;

        mostJumps.setText(stats.getMostJumpsString());
        longestSession.setText(stats.getMostDurationString());
        fastestJumping.setText(stats.getMostAvgString());

        weekDuration.setText(stats.getWeekDurationString());
        weekJumps.setText(stats.getWeekJumpString());
        weekAvg.setText(stats.getWeekAvgString());
    }
}