package com.pieta.jumpscounter.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pieta.jumpscounter.MainActivity;
import com.pieta.jumpscounter.R;

public class CounterFragment extends Fragment implements View.OnClickListener {

    private final MainActivity mainActivity;

    private TextView counter;
    private Button startButton;
    private Button pauseButton;
    private Button resumeButton;
    private Button stopButton;

    public CounterFragment(MainActivity activity) {
        super(R.layout.fragment_counter);
        mainActivity = activity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        counter = (TextView) getView().findViewById(R.id.jumps_counter);
        startButton = (Button) view.findViewById(R.id.start_button);
        pauseButton = (Button) view.findViewById(R.id.pause_button);
        resumeButton = (Button) view.findViewById(R.id.resume_button);
        stopButton = (Button) view.findViewById(R.id.stop_button);
        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        resumeButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }

    public void updateCounter(int value) {
        counter.setText(Integer.toString(value));
    }

    public void updateButtons(boolean hideStart, boolean hidePause, boolean hideResume, boolean hideStop) {
        toggleButtonVisibility(startButton, hideStart);
        toggleButtonVisibility(pauseButton, hidePause);
        toggleButtonVisibility(resumeButton, hideResume);
        toggleButtonVisibility(stopButton, hideStop);
    }

    private void toggleButtonVisibility(Button button, boolean isHidden) {
        if(isHidden) button.setVisibility(View.GONE);
        else button.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                mainActivity.switchState(MainActivity.State.START);
                break;
            case R.id.pause_button:
                mainActivity.switchState(MainActivity.State.PAUSE);
                break;
            case R.id.resume_button:
                mainActivity.switchState(MainActivity.State.RESUME);
                break;
            case R.id.stop_button:
                mainActivity.switchState(MainActivity.State.STOP);
                break;
        }
    }
}