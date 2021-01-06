package com.pieta.jumpscounter.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pieta.jumpscounter.MainActivity;
import com.pieta.jumpscounter.R;

public class CounterFragment extends Fragment implements View.OnClickListener {

    private TextView counter;
    private MainActivity mainActivity;

    public CounterFragment(MainActivity activity) {
        super(R.layout.fragment_counter);
        mainActivity = activity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        counter = (TextView) getView().findViewById(R.id.jumps_counter);
        Button button = (Button) view.findViewById(R.id.stat_button);
        button.setOnClickListener(this);
    }

    public void updateCounter(int value) {
        counter.setText(Integer.toString(value));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stat_button:
                mainActivity.switchState(MainActivity.State.START);
                break;
        }
    }
}