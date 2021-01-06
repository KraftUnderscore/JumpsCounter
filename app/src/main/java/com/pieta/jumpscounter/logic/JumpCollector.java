package com.pieta.jumpscounter.logic;

import android.widget.TextView;

import com.pieta.jumpscounter.fragments.CounterFragment;

public class JumpCollector {
    private int jumps = 0;
    private final TextView counter;

    public JumpCollector(TextView counter) {
        this.counter = counter;
    }

    public void collectJump() {
        jumps += 1;
        counter.setText(Integer.toString(getJumps()));
    }

    public int getJumps() {
        return Math.max(0, jumps / 2 - 1);
    }
}
