package com.pieta.jumpscounter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        sessionDate = view.findViewById(R.id.summary_date_text);
        sessionJumps = view.findViewById(R.id.summary_jumps_text);
        sessionDuration = view.findViewById(R.id.summary_duration_text);
        sessionAvg = view.findViewById(R.id.summary_avg_text);

        Button shareButton = view.findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareSession();
            }
        });

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
    }

    private void shareSession() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                             getResources().getString(R.string.share_text, session.jumps));
        shareIntent.setType("text/plain");

        Intent startIntent = Intent.createChooser(shareIntent, null);
        startActivity(startIntent);
    }
}