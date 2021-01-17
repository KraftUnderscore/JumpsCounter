package com.pieta.jumpscounter.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pieta.jumpscounter.MainActivity;
import com.pieta.jumpscounter.R;

import java.util.Locale;

public class CounterFragment extends Fragment implements View.OnClickListener {

    private final MainActivity mainActivity;

    private TextView counter;
    private ImageButton startButton;
    private ImageButton pauseButton;
    private ImageButton resumeButton;
    private ImageButton stopButton;

    public CounterFragment(MainActivity activity) {
        super(R.layout.fragment_counter);
        mainActivity = activity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        counter = view.findViewById(R.id.jumps_counter);
        startButton = view.findViewById(R.id.start_button);
        pauseButton = view.findViewById(R.id.pause_button);
        resumeButton = view.findViewById(R.id.resume_button);
        stopButton = view.findViewById(R.id.stop_button);
        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        resumeButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.start_button) {
            mainActivity.switchState(MainActivity.State.START);
        } else if(view.getId() == R.id.pause_button) {
            mainActivity.switchState(MainActivity.State.PAUSE);
        } else if(view.getId() == R.id.resume_button) {
            mainActivity.switchState(MainActivity.State.RESUME);
        } else if(view.getId() == R.id.stop_button) {
            mainActivity.switchState(MainActivity.State.STOP);
        }
    }

    public void updateCounter(int value) {
        counter.setText(String.format(Locale.getDefault(), "%d", value));
    }

    public void updateButtons(boolean hideStart, boolean hidePause, boolean hideResume, boolean hideStop) {
        toggleButtonVisibility(startButton, hideStart);
        toggleButtonVisibility(pauseButton, hidePause);
        toggleButtonVisibility(resumeButton, hideResume);
        toggleButtonVisibility(stopButton, hideStop);
    }

    private void toggleButtonVisibility(ImageButton button, boolean isHidden) {
        if(isHidden) button.setVisibility(View.INVISIBLE);
        else button.setVisibility(View.VISIBLE);
    }
}