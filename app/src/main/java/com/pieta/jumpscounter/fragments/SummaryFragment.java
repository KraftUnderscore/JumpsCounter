package com.pieta.jumpscounter.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pieta.jumpscounter.R;
import com.pieta.jumpscounter.data.Session;

public class SummaryFragment extends Fragment {

    private TextView sessionDate;
    private TextView sessionJumps;
    private TextView sessionDuration;
    private TextView sessionAvg;

    private Session session;

    public SummaryFragment() {
        super(R.layout.fragment_summary);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionDate = (TextView) view.findViewById(R.id.summary_date_text);
        sessionJumps = (TextView) view.findViewById(R.id.summary_jumps_text);
        sessionDuration = (TextView) view.findViewById(R.id.summary_duration_text);
        sessionAvg = (TextView) view.findViewById(R.id.summary_avg_text);

        updateSummaryText();
    }

    public void updateSessionData(Session data) {
        session = data;
    }

    private void updateSummaryText() {
        if(session == null) return;

        sessionJumps.setText(session.getJumpsString());
        sessionDuration.setText(session.getDurationString());
        sessionAvg.setText(session.getAvgString());
        sessionDate.setText(session.getDateString());

        session = null;
    }
}