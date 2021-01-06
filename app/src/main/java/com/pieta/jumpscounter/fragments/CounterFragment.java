package com.pieta.jumpscounter.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pieta.jumpscounter.R;

public class CounterFragment extends Fragment {

    private TextView hejo;

    public CounterFragment() {
        super(R.layout.fragment_counter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hejo = (TextView) getView().findViewById(R.id.jumps_counter);
        hejo.setText("ELO MORDO");
    }

    public void Test(int kupa) {
        hejo.setText("DUPA: " + Integer.toString(kupa));
    }
}